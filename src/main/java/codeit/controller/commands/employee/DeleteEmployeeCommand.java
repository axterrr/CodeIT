package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.services.EmployeeService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteEmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String employeeId = request.getParameter(Attribute.EMPLOYEE_ID);
        EmployeeService.getInstance().deleteEmployee(employeeId);
        redirectToAllEmployeesPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllEmployeesPageWithSuccessMessage(HttpServletRequest request,
                                                            HttpServletResponse response) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Employee successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.EMPLOYEES, urlParams);
    }
}
