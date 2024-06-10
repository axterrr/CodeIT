package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.controller.utils.SessionManager;
import codeit.models.entities.Employee;
import codeit.models.entities.Project;
import codeit.models.entities.Task;
import codeit.models.enums.Role;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class EmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String employeeId = request.getParameter(Attribute.EMPLOYEE_ID);
        Employee employee = EmployeeService.getInstance().getEmployeeById(employeeId);
        request.setAttribute(Attribute.EMPLOYEE, employee);

        Employee loggedInEmployee = SessionManager.getInstance().getEmployeeFromSession(request.getSession());

        if(loggedInEmployee.getRole() == Role.DEVELOPER && loggedInEmployee.getId().equals(employee.getId())) {
            request.setAttribute(Attribute.TASKS, TaskService.getInstance().getAllTasksByDeveloper(employeeId));
            return Page.EMPLOYEE_VIEW;
        }

        if(loggedInEmployee.getRole() == Role.TESTER && loggedInEmployee.getId().equals(employee.getId())) {
            request.setAttribute(Attribute.TASKS, TaskService.getInstance().getAllTasksByTester(employeeId));
            return Page.EMPLOYEE_VIEW;
        }

        if (employee.getRole() == Role.PROJECT_MANAGER) {
            List<Project> projects = new ArrayList<>();

            if (loggedInEmployee.getRole() == Role.CEO)
                projects = ProjectService.getInstance().getAllProjectsByManager(employeeId);

            if (loggedInEmployee.getRole() == Role.PROJECT_MANAGER)
                projects = ProjectService.getInstance().getAllProjectsByManager(employeeId).stream()
                        .filter(project -> project.getManager()!=null && project.getManager().getId().equals(loggedInEmployee.getId()))
                        .toList();

            request.setAttribute(Attribute.PROJECTS, projects);
        }


        if (employee.getRole() == Role.DEVELOPER) {
            List<Task> tasks = new ArrayList<>();

            if (loggedInEmployee.getRole() == Role.CEO)
                tasks = TaskService.getInstance().getAllTasksByDeveloper(employeeId);

            if (loggedInEmployee.getRole() == Role.PROJECT_MANAGER)
                tasks = TaskService.getInstance().getAllTasksByDeveloper(employeeId).stream()
                        .filter(task -> task.getProject().getManager()!=null
                                && task.getProject().getManager().getId().equals(loggedInEmployee.getId()))
                        .toList();

            request.setAttribute(Attribute.TASKS, tasks);
        }


        if (employee.getRole() == Role.TESTER) {
            List<Task> tasks = new ArrayList<>();

            if (loggedInEmployee.getRole() == Role.CEO)
                tasks = TaskService.getInstance().getAllTasksByTester(employeeId);

            if (loggedInEmployee.getRole() == Role.PROJECT_MANAGER)
                tasks = TaskService.getInstance().getAllTasksByTester(employeeId).stream()
                        .filter(task -> task.getProject().getManager()!=null
                                && task.getProject().getManager().getId().equals(loggedInEmployee.getId()))
                        .toList();

            request.setAttribute(Attribute.TASKS, tasks);
        }

        return Page.EMPLOYEE_VIEW;
    }
}

