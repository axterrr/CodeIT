package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.dto.ClientDto;
import codeit.services.ClientService;
import codeit.services.EmployeeService;
import codeit.validators.entities.ClientDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateClientCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ClientDto clientDto = getUserInput(request);
        List<String> errors = ClientDtoValidator.getInstance().validate(clientDto);

        if (errors.isEmpty()) {

            if (EmployeeService.getInstance().getEmployeeByEmail(clientDto.getEmail()) != null
                    || (ClientService.getInstance().getClientByEmail(clientDto.getEmail()) != null
                    && !ClientService.getInstance().getClientByEmail(clientDto.getEmail()).getId().equals(clientDto.getId()))) {
                errors.add("Email is already taken");
                addRequestAttributes(request, clientDto, errors);
                return Page.ADD_UPDATE_CLIENT_VIEW;
            }

            ClientService.getInstance().updateClient(clientDto.toClient());
            redirectToAllClientsPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(request, clientDto, errors);
        return Page.ADD_UPDATE_CLIENT_VIEW;
    }

    private ClientDto getUserInput(HttpServletRequest request) {
        return new ClientDto.Builder()
                .setId(request.getParameter(Attribute.CLIENT_ID))
                .setName(request.getParameter(Attribute.NAME))
                .setPerson(request.getParameter(Attribute.PERSON))
                .setEmail(request.getParameter(Attribute.EMAIL))
                .setPhone(request.getParameter(Attribute.PHONE))
                .setAddress(request.getParameter(Attribute.ADDRESS))
                .setDescription(request.getParameter(Attribute.DESCRIPTION))
                .setPassword(request.getParameter(Attribute.PASSWORD))
                .setConfirmPassword(request.getParameter(Attribute.CONFIRM_PASSWORD))
                .build();
    }

    private void redirectToAllClientsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Client successfully updated");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.CLIENTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, ClientDto clientDto, List<String> errors) {
        request.setAttribute(Attribute.CLIENT_DTO, clientDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
