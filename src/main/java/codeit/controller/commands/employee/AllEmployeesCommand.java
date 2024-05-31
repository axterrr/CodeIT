package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Employee;
import codeit.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllEmployeesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = EmployeeService.getInstance().getAllEmployees();
        request.setAttribute(Attribute.EMPLOYEES, employees);
        return Page.ALL_EMPLOYEES_VIEW;
    }
}
