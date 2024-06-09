package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Task;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllTasksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Task> tasks = TaskService.getInstance().getAllTasks();

        tasks = searchByName(tasks, request);
        tasks = filterByStatuses(tasks, request);
        tasks = filterByProjects(tasks, request);
        tasks = filterByDevelopers(tasks, request);
        tasks = filterByTesters(tasks, request);
        tasks = filterByDates(tasks, request);
        sort(tasks, request);

        request.setAttribute(Attribute.PROJECTS, ProjectService.getInstance().getAllProjects());
        request.setAttribute(Attribute.DEVELOPERS, EmployeeService.getInstance().getAllDevelopers());
        request.setAttribute(Attribute.TESTERS, EmployeeService.getInstance().getAllTesters());
        request.setAttribute(Attribute.TASKS, tasks);
        return Page.ALL_TASKS_VIEW;
    }

    private List<Task> searchByName(List<Task> tasks, HttpServletRequest request) {
        String searchName = request.getParameter(Attribute.NAME);
        if (searchName != null && !searchName.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> task.getName().toLowerCase().contains(searchName.toLowerCase()))
                    .toList();
            request.setAttribute(Attribute.NAME, searchName);
        }
        return new ArrayList<>(tasks);
    }

    private List<Task> filterByStatuses(List<Task> tasks, HttpServletRequest request) {
        String[] statuses = request.getParameterValues(Attribute.STATUSES);
        List<String> statusesList = (statuses == null) ? new ArrayList<>() : List.of(statuses);
        if (!statusesList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> statusesList.contains(task.getStatus().getValue()))
                    .toList();
            request.setAttribute(Attribute.STATUSES, statusesList);
        }
        return new ArrayList<>(tasks);
    }

    private List<Task> filterByProjects(List<Task> tasks, HttpServletRequest request) {
        String[] projects = request.getParameterValues(Attribute.SELECTED_PROJECTS);
        List<String> projectsList = (projects == null) ? new ArrayList<>() : List.of(projects);
        if (!projectsList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> projectsList.contains(task.getProject().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_PROJECTS, projectsList.stream()
                    .map(projectId -> ProjectService.getInstance().getProjectById(projectId))
                    .toList());
        }
        return new ArrayList<>(tasks);
    }

    private List<Task> filterByDevelopers(List<Task> tasks, HttpServletRequest request) {
        String[] developers = request.getParameterValues(Attribute.SELECTED_DEVELOPERS);
        List<String> developersList = (developers == null) ? new ArrayList<>() : List.of(developers);
        if (!developersList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> task.getDeveloper()!=null && developersList.contains(task.getDeveloper().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_DEVELOPERS, developersList.stream()
                    .map(developerId -> EmployeeService.getInstance().getEmployeeById(developerId))
                    .toList());
        }
        return new ArrayList<>(tasks);
    }

    private List<Task> filterByTesters(List<Task> tasks, HttpServletRequest request) {
        String[] testers = request.getParameterValues(Attribute.SELECTED_TESTERS);
        List<String> testersList = (testers == null) ? new ArrayList<>() : List.of(testers);
        if (!testersList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> task.getTester()!= null && testersList.contains(task.getTester().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_TESTERS, testersList.stream()
                    .map(testerId -> EmployeeService.getInstance().getEmployeeById(testerId))
                    .toList());
        }
        return new ArrayList<>(tasks);
    }

    private List<Task> filterByDates(List<Task> tasks, HttpServletRequest request) {
        String startFrom = request.getParameter(Attribute.START_DATE_FROM);
        String startTo = request.getParameter(Attribute.START_DATE_TO);
        String dueFrom = request.getParameter(Attribute.DUE_DATE_FROM);
        String dueTo = request.getParameter(Attribute.DUE_DATE_TO);

        tasks = tasks.stream()
            .filter(task ->
                (startFrom == null || startFrom.isEmpty()
                    || task.getStartDate().isAfter(LocalDate.parse(startFrom).minusDays(1).atStartOfDay()))
                && (startTo == null || startTo.isEmpty()
                    || task.getStartDate().isBefore(LocalDate.parse(startTo).plusDays(1).atStartOfDay()))
                && (dueFrom == null || dueFrom.isEmpty()
                    || task.getDueDate().isAfter(LocalDate.parse(dueFrom).minusDays(1).atStartOfDay()))
                && (dueTo == null || dueTo.isEmpty()
                    || task.getDueDate().isBefore(LocalDate.parse(dueTo).plusDays(1).atStartOfDay()))
            ).toList();

        if(startFrom != null)
            request.setAttribute(Attribute.START_DATE_FROM, startFrom);
        if(startTo != null)
            request.setAttribute(Attribute.START_DATE_TO, startTo);
        if(dueFrom != null)
            request.setAttribute(Attribute.DUE_DATE_FROM, dueFrom);
        if(dueTo != null)
            request.setAttribute(Attribute.DUE_DATE_TO, dueTo);

        return new ArrayList<>(tasks);
    }

    private void sort(List<Task> tasks, HttpServletRequest request) {
        String sortBy = request.getParameter(Attribute.SORT_BY);
        String descending = request.getParameter(Attribute.DESCENDING);

        Comparator<Task> comparator = (sortBy == null) ?
                Comparator.comparing(task -> task.getName().toLowerCase()) :
                switch (sortBy) {
                    case "startDate" -> Comparator.comparing(Task::getStartDate);
                    case "dueDate" -> Comparator.comparing(Task::getDueDate);
                    case "status" -> Comparator.comparing(Task::getStatus);
                    default -> Comparator.comparing(task -> task.getName().toLowerCase());
                };

        if (descending != null && descending.equals("on"))
            comparator = comparator.reversed();
        tasks.sort(comparator);

        if(sortBy != null)
            request.setAttribute(Attribute.SORT_BY, sortBy);
        if(descending != null)
            request.setAttribute(Attribute.DESCENDING, descending);
    }
}
