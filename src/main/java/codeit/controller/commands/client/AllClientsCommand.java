package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Client;
import codeit.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllClientsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Client> clients = ClientService.getInstance().getAllClients();
        request.setAttribute(Attribute.CLIENTS, clients);
        return Page.ALL_CLIENTS_VIEW;
    }
}
