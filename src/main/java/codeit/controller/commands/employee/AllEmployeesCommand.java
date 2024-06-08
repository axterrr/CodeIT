package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Employee;
import codeit.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AllEmployeesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = EmployeeService.getInstance().getAllEmployees();

        String[] roles = request.getParameterValues(Attribute.ROLES);
        List<String> rolesList = (roles == null) ? new ArrayList<>() : List.of(roles);
        if (!rolesList.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> rolesList.contains(employee.getRole().getValue()))
                    .toList();
            request.setAttribute(Attribute.ROLES, rolesList);
        }

        request.setAttribute(Attribute.EMPLOYEES, employees);
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
