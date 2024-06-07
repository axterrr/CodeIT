package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.services.ClientService;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String clientId = request.getParameter(Attribute.CLIENT_ID);
        request.setAttribute(Attribute.CLIENT, ClientService.getInstance().getClientById(clientId));
        request.setAttribute(Attribute.ORDERS, OrderService.getInstance().getAllOrdersByClient(clientId));
        return Page.CLIENT_VIEW;
    }
}

