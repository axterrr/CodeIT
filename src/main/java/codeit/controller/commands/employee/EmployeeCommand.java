package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Employee;
import codeit.models.enums.Role;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String employeeId = request.getParameter(Attribute.EMPLOYEE_ID);
        Employee employee = EmployeeService.getInstance().getEmployeeById(employeeId);
        request.setAttribute(Attribute.EMPLOYEE, employee);

        if (employee.getRole() == Role.PROJECT_MANAGER)
            request.setAttribute(Attribute.PROJECTS, ProjectService.getInstance().getAllProjectsByManager(employeeId));

        if (employee.getRole() == Role.DEVELOPER)
            request.setAttribute(Attribute.TASKS, TaskService.getInstance().getAllTasksByDeveloper(employeeId));

        if (employee.getRole() == Role.TESTER)
            request.setAttribute(Attribute.TASKS, TaskService.getInstance().getAllTasksByTester(employeeId));

        return Page.EMPLOYEE_VIEW;
    }
}

