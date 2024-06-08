package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Client;
import codeit.models.entities.Order;
import codeit.services.ClientService;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = OrderService.getInstance().getAllOrders();

        String[] statuses = request.getParameterValues(Attribute.STATUSES);
        List<String> statusesList = (statuses == null) ? new ArrayList<>() : List.of(statuses);
        if (!statusesList.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> statusesList.contains(order.getStatus().getValue()))
                    .toList();
            request.setAttribute(Attribute.STATUSES, statusesList);
        }

        String[] clients = request.getParameterValues(Attribute.SELECTED_CLIENTS);
        List<String> clientsList = (clients == null) ? new ArrayList<>() : List.of(clients);
        if (!clientsList.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> clientsList.contains(order.getClient().getId()))
                    .toList();
            request.setAttribute(Attribute.SELECTED_CLIENTS, clientsList.stream()
                    .map(clientId -> ClientService.getInstance().getClientById(clientId))
                    .toList());
        }

        String startFrom = request.getParameter(Attribute.START_DATE_FROM);
        String startTo = request.getParameter(Attribute.START_DATE_TO);
        String dueFrom = request.getParameter(Attribute.DUE_DATE_FROM);
        String dueTo = request.getParameter(Attribute.DUE_DATE_TO);

        orders = orders.stream()
                .filter(order ->
                        (startFrom == null || startFrom.isEmpty()
                            || order.getCreationDate().isAfter(LocalDate.parse(startFrom).minusDays(1).atStartOfDay()))
                        && (startTo == null || startTo.isEmpty()
                            || order.getCreationDate().isBefore(LocalDate.parse(startTo).plusDays(1).atStartOfDay()))
                        && (dueFrom == null || dueFrom.isEmpty()
                            || order.getDueDate().isAfter(LocalDate.parse(dueFrom).minusDays(1).atStartOfDay()))
                        && (dueTo == null || dueTo.isEmpty()
                        || order.getDueDate().isBefore(LocalDate.parse(dueTo).plusDays(1).atStartOfDay()))
                ).toList();

        if(startFrom != null)
            request.setAttribute(Attribute.START_DATE_FROM, startFrom);
        if(startTo != null)
            request.setAttribute(Attribute.START_DATE_TO, startTo);
        if(dueFrom != null)
            request.setAttribute(Attribute.DUE_DATE_FROM, dueFrom);
        if(dueTo != null)
            request.setAttribute(Attribute.DUE_DATE_TO, dueTo);

        request.setAttribute(Attribute.ORDERS, orders);
        request.setAttribute(Attribute.CLIENTS, ClientService.getInstance().getAllClients());
        return Page.ALL_ORDERS_VIEW;
    }
}
