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
import java.util.List;

public class AllTasksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Task> tasks = TaskService.getInstance().getAllTasks();

        String[] statuses = request.getParameterValues(Attribute.STATUSES);
        List<String> statusesList = (statuses == null) ? new ArrayList<>() : List.of(statuses);
        if (!statusesList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> statusesList.contains(task.getStatus().getValue()))
                    .toList();
            request.setAttribute(Attribute.STATUSES, statusesList);
        }

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

        String[] developers = request.getParameterValues(Attribute.SELECTED_DEVELOPERS);
        List<String> developersList = (developers == null) ? new ArrayList<>() : List.of(developers);
        if (!developersList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> developersList.contains(task.getDeveloper().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_DEVELOPERS, developersList.stream()
                    .map(developerId -> EmployeeService.getInstance().getEmployeeById(developerId))
                    .toList());
        }

        String[] testers = request.getParameterValues(Attribute.SELECTED_TESTERS);
        List<String> testersList = (testers == null) ? new ArrayList<>() : List.of(testers);
        if (!testersList.isEmpty()) {
            tasks = tasks.stream()
                    .filter(task -> testersList.contains(task.getTester().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_TESTERS, testersList.stream()
                    .map(testerId -> EmployeeService.getInstance().getEmployeeById(testerId))
                    .toList());
        }

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

        request.setAttribute(Attribute.PROJECTS, ProjectService.getInstance().getAllProjects());
        request.setAttribute(Attribute.DEVELOPERS, EmployeeService.getInstance().getAllDevelopers());
        request.setAttribute(Attribute.TESTERS, EmployeeService.getInstance().getAllTesters());
        request.setAttribute(Attribute.TASKS, tasks);
        return Page.ALL_TASKS_VIEW;
    }
}
