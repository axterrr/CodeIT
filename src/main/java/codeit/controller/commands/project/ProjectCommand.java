package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.controller.utils.SessionManager;
import codeit.models.entities.Employee;
import codeit.models.entities.Task;
import codeit.models.enums.Role;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        request.setAttribute(Attribute.PROJECT, ProjectService.getInstance().getProjectById(projectId));

        List<Task> tasks = TaskService.getInstance().getAllTasksByProject(projectId);
        Employee loggedInEmployee = SessionManager.getInstance().getEmployeeFromSession(request.getSession());

        if (loggedInEmployee.getRole() == Role.PROJECT_MANAGER) {
            tasks = tasks.stream()
                    .filter(task -> task.getProject().getManager().getId().equals(loggedInEmployee.getId()))
                    .toList();
        }
        if (loggedInEmployee.getRole() == Role.DEVELOPER) {
            tasks = tasks.stream()
                    .filter(task -> task.getDeveloper()!=null && task.getDeveloper().getId().equals(loggedInEmployee.getId()))
                    .toList();
        }
        if (loggedInEmployee.getRole() == Role.TESTER) {
            tasks = tasks.stream()
                    .filter(task -> task.getTester()!=null && task.getTester().getId().equals(loggedInEmployee.getId()))
                    .toList();
        }

        request.setAttribute(Attribute.TASKS, tasks);
        return Page.PROJECT_VIEW;
    }
}
