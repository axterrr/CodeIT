package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Employee;
import codeit.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateEmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String employeeId = request.getParameter(Attribute.EMPLOYEE_ID);
        Employee employee = EmployeeService.getInstance().getEmployeeById(employeeId);
        request.setAttribute(Attribute.EMPLOYEE_DTO, employee);
        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }
}
