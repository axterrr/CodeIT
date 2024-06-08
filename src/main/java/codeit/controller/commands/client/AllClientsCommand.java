package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Client;
import codeit.services.ClientService;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class AllClientsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Client> clients = ClientService.getInstance().getAllClients();

        clients = searchByName(clients, request);
        sort(clients, request);

        request.setAttribute(Attribute.CLIENTS, clients);
        return Page.ALL_CLIENTS_VIEW;
    }

    private List<Client> searchByName(List<Client> clients, HttpServletRequest request) {
        String searchName = request.getParameter(Attribute.NAME);
        if (searchName != null && !searchName.isEmpty()) {
            clients = clients.stream()
                    .filter(client -> client.getName().toLowerCase().contains(searchName.toLowerCase()))
                    .toList();
            request.setAttribute(Attribute.NAME, searchName);
        }
        return new ArrayList<>(clients);
    }

    private void sort(List<Client> clients, HttpServletRequest request) {
        String sortBy = request.getParameter(Attribute.SORT_BY);
        String descending = request.getParameter(Attribute.DESCENDING);

        Comparator<Client> comparator = (sortBy == null) ?
                Comparator.comparing(client -> client.getName().toLowerCase()) :
            switch (sortBy) {
                case "registrationDate" -> Comparator.comparing(Client::getRegistrationDate);
                case "ordersAmount" -> Comparator.comparing(client ->
                        OrderService.getInstance().getAllOrdersByClient(client.getId()).size());
                default -> Comparator.comparing(client -> client.getName().toLowerCase());
            };

        if (descending != null && descending.equals("on"))
            comparator = comparator.reversed();
        clients.sort(comparator);

        if(sortBy != null)
            request.setAttribute(Attribute.SORT_BY, sortBy);
        if(descending != null)
            request.setAttribute(Attribute.DESCENDING, descending);
    }
}
