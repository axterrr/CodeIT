package codeit.controller.commands.auth;

import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.constants.Attribute;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.controller.utils.SessionManager;
import codeit.dto.CredentialsDto;
import codeit.models.entities.Client;
import codeit.models.entities.Employee;
import codeit.services.ClientService;
import codeit.services.EmployeeService;
import codeit.validators.entities.CredentialsDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class PostLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();

        if (SessionManager.getInstance().isUserLoggedIn(session)) {
            RedirectionManager.getInstance().redirect(request, response, ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        CredentialsDto credentialsDto = new CredentialsDto(request.getParameter(Attribute.EMAIL),
                request.getParameter(Attribute.PASSWORD));
        List<String> errors = CredentialsDtoValidator.getInstance().validate(credentialsDto);

        if (!errors.isEmpty()) {
            addRequestAttributes(request, credentialsDto, errors);
            return Page.LOGIN_VIEW;
        }

        Employee employee = EmployeeService.getInstance().getEmployeeByCredentials(credentialsDto);
        if (employee != null) {
            SessionManager.getInstance().addEmployeeToSession(session, employee);
            RedirectionManager.getInstance().redirect(request, response, ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        Client client = ClientService.getInstance().getClientByCredentials(credentialsDto);
        if (client != null) {
            SessionManager.getInstance().addClientToSession(session, client);
            RedirectionManager.getInstance().redirect(request, response, ServletPath.HOME);
            return RedirectionManager.REDIRECTION;
        }

        errors.add("Login or Password is not correct");
        addRequestAttributes(request, credentialsDto, errors);
        return Page.LOGIN_VIEW;
    }

    private void addRequestAttributes(HttpServletRequest request, CredentialsDto credentialsDto, List<String> errors) {
        request.setAttribute(Attribute.LOGIN_USER, credentialsDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
