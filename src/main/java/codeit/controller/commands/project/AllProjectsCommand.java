package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Project;
import codeit.models.entities.Task;
import codeit.services.ClientService;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllProjectsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Project> projects = ProjectService.getInstance().getAllProjects();

        projects = searchByName(projects, request);
        projects = filterByStatuses(projects, request);
        projects = filterByManagers(projects, request);
        projects = filterByDates(projects, request);
        sort(projects, request);

        request.setAttribute(Attribute.PROJECTS, projects);
        request.setAttribute(Attribute.MANAGERS, EmployeeService.getInstance().getAllManagers());
        return Page.ALL_PROJECTS_VIEW;
    }

    private List<Project> searchByName(List<Project> projects, HttpServletRequest request) {
        String searchName = request.getParameter(Attribute.NAME);
        if (searchName != null && !searchName.isEmpty()) {
            projects = projects.stream()
                    .filter(project -> project.getName().toLowerCase().contains(searchName.toLowerCase()))
                    .toList();
            request.setAttribute(Attribute.NAME, searchName);
        }
        return new ArrayList<>(projects);
    }

    private List<Project> filterByStatuses(List<Project> projects, HttpServletRequest request) {
        String[] statuses = request.getParameterValues(Attribute.STATUSES);
        List<String> statusesList = (statuses == null) ? new ArrayList<>() : List.of(statuses);
        if (!statusesList.isEmpty()) {
            projects = projects.stream()
                    .filter(project -> statusesList.contains(project.getStatus().getValue()))
                    .toList();
            request.setAttribute(Attribute.STATUSES, statusesList);
        }
        return new ArrayList<>(projects);
    }

    private List<Project> filterByManagers(List<Project> projects, HttpServletRequest request) {
        String[] managers = request.getParameterValues(Attribute.SELECTED_MANAGERS);
        List<String> managersList = (managers == null) ? new ArrayList<>() : List.of(managers);
        if (!managersList.isEmpty()) {
            projects = projects.stream()
                    .filter(project -> managersList.contains(project.getManager().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_MANAGERS, managersList.stream()
                    .map(managerId -> EmployeeService.getInstance().getEmployeeById(managerId))
                    .toList());
        }
        return new ArrayList<>(projects);
    }

    private List<Project> filterByDates(List<Project> projects, HttpServletRequest request) {
        String startFrom = request.getParameter(Attribute.START_DATE_FROM);
        String startTo = request.getParameter(Attribute.START_DATE_TO);
        String dueFrom = request.getParameter(Attribute.DUE_DATE_FROM);
        String dueTo = request.getParameter(Attribute.DUE_DATE_TO);

        projects = projects.stream()
            .filter(project ->
                (startFrom == null || startFrom.isEmpty()
                    || project.getStartDate().isAfter(LocalDate.parse(startFrom).minusDays(1).atStartOfDay()))
                && (startTo == null || startTo.isEmpty()
                    || project.getStartDate().isBefore(LocalDate.parse(startTo).plusDays(1).atStartOfDay()))
                && (dueFrom == null || dueFrom.isEmpty()
                    || project.getDueDate().isAfter(LocalDate.parse(dueFrom).minusDays(1).atStartOfDay()))
                && (dueTo == null || dueTo.isEmpty()
                    || project.getDueDate().isBefore(LocalDate.parse(dueTo).plusDays(1).atStartOfDay()))
            ).toList();

        if(startFrom != null)
            request.setAttribute(Attribute.START_DATE_FROM, startFrom);
        if(startTo != null)
            request.setAttribute(Attribute.START_DATE_TO, startTo);
        if(dueFrom != null)
            request.setAttribute(Attribute.DUE_DATE_FROM, dueFrom);
        if(dueTo != null)
            request.setAttribute(Attribute.DUE_DATE_TO, dueTo);

        return new ArrayList<>(projects);
    }

    private void sort(List<Project> projects, HttpServletRequest request) {
        String sortBy = request.getParameter(Attribute.SORT_BY);
        String descending = request.getParameter(Attribute.DESCENDING);

        Comparator<Project> comparator = (sortBy == null) ?
                Comparator.comparing(project -> project.getName().toLowerCase()) :
            switch (sortBy) {
                case "startDate" -> Comparator.comparing(Project::getStartDate);
                case "dueDate" -> Comparator.comparing(Project::getDueDate);
                case "status" -> Comparator.comparing(Project::getStatus);
                case "budget" -> Comparator.comparing(Project::getBudget);
                case "tasksNumber" -> Comparator.comparing(project ->
                        TaskService.getInstance().getAllTasksByProject(project.getId()).size());
                case "employeesNumber" -> Comparator.comparing(project ->
                        ProjectService.getInstance().getEmployeesNumberOnProject(project.getId()));
                default -> Comparator.comparing(project -> project.getName().toLowerCase());
            };

        if (descending != null && descending.equals("on"))
            comparator = comparator.reversed();
        projects.sort(comparator);

        if(sortBy != null)
            request.setAttribute(Attribute.SORT_BY, sortBy);
        if(descending != null)
            request.setAttribute(Attribute.DESCENDING, descending);
    }
}
