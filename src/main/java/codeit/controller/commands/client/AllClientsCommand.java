package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.controller.utils.SessionManager;
import codeit.models.entities.Client;
import codeit.models.entities.Employee;
import codeit.models.enums.Role;
import codeit.services.ClientService;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AllClientsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Client loggedInClient = SessionManager.getInstance().getClientFromSession(request.getSession());
        if (loggedInClient != null) {
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put(Attribute.CLIENT_ID, loggedInClient.getId());
            RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.CLIENT, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        List<Client> clients = new ArrayList<>();

        Employee loggedInEmployee = SessionManager.getInstance().getEmployeeFromSession(request.getSession());
        if (loggedInEmployee != null && loggedInEmployee.getRole() == Role.CEO)
            clients = ClientService.getInstance().getAllClients();

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
