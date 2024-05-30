package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Client;
import codeit.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateClientCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(Attribute.CLIENT_ID);
        Client client = ClientService.getInstance().getClientById(clientId);
        request.setAttribute(Attribute.CLIENT_DTO, client);
        return Page.ADD_UPDATE_CLIENT_VIEW;
    }
}
