package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Project;
import codeit.services.ClientService;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllProjectsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Project> projects = ProjectService.getInstance().getAllProjects();

        String searchName = request.getParameter(Attribute.NAME);
        if (searchName != null && !searchName.isEmpty()) {
            projects = projects.stream()
                    .filter(project -> project.getName().toLowerCase().contains(searchName.toLowerCase()))
                    .toList();
            request.setAttribute(Attribute.NAME, searchName);
        }

        String[] statuses = request.getParameterValues(Attribute.STATUSES);
        List<String> statusesList = (statuses == null) ? new ArrayList<>() : List.of(statuses);
        if (!statusesList.isEmpty()) {
            projects = projects.stream()
                    .filter(project -> statusesList.contains(project.getStatus().getValue()))
                    .toList();
            request.setAttribute(Attribute.STATUSES, statusesList);
        }

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

        request.setAttribute(Attribute.PROJECTS, projects);
        request.setAttribute(Attribute.MANAGERS, EmployeeService.getInstance().getAllManagers());
        return Page.ALL_PROJECTS_VIEW;
    }
}
