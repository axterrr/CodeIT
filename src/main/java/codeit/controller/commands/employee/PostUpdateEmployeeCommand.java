package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.dto.EmployeeDto;
import codeit.services.ClientService;
import codeit.services.EmployeeService;
import codeit.validators.entities.EmployeeDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateEmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        EmployeeDto employeeDto = getUserInput(request);
        List<String> errors = EmployeeDtoValidator.getInstance().validate(employeeDto);

        if (errors.isEmpty()) {

            if (ClientService.getInstance().getClientByEmail(employeeDto.getEmail()) != null
                    || (EmployeeService.getInstance().getEmployeeByEmail(employeeDto.getEmail()) != null
                    && !EmployeeService.getInstance().getEmployeeByEmail(employeeDto.getEmail()).getId().equals(employeeDto.getId()))) {
                errors.add("Email is already taken");
                addRequestAttributes(request, employeeDto, errors);
                return Page.ADD_UPDATE_EMPLOYEE_VIEW;
            }

            EmployeeService.getInstance().updateEmployee(employeeDto.toEmployee());
            redirectToAllEmployeesPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(request, employeeDto, errors);
        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }

    private EmployeeDto getUserInput(HttpServletRequest request) {
        return new EmployeeDto.Builder()
                .setId(request.getParameter(Attribute.EMPLOYEE_ID))
                .setFirstName(request.getParameter(Attribute.FIRST_NAME))
                .setLastName(request.getParameter(Attribute.LAST_NAME))
                .setRole(request.getParameter(Attribute.ROLE))
                .setSpecialisation(request.getParameter(Attribute.SPECIALISATION))
                .setSalary(request.getParameter(Attribute.SALARY))
                .setEmail(request.getParameter(Attribute.EMAIL))
                .setPhone(request.getParameter(Attribute.PHONE))
                .setAddress(request.getParameter(Attribute.ADDRESS))
                .setBirthDate(request.getParameter(Attribute.BIRTH_DATE))
                .setPassword(request.getParameter(Attribute.PASSWORD))
                .setConfirmPassword(request.getParameter(Attribute.CONFIRM_PASSWORD))
                .build();
    }

    private void redirectToAllEmployeesPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Employee successfully updated");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.EMPLOYEES, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, EmployeeDto employeeDto, List<String> errors) {
        request.setAttribute(Attribute.EMPLOYEE_DTO, employeeDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
