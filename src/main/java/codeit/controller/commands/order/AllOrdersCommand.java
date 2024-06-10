package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.controller.utils.SessionManager;
import codeit.models.entities.Client;
import codeit.models.entities.Employee;
import codeit.models.entities.Order;
import codeit.models.enums.Role;
import codeit.services.ClientService;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

public class AllOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Order> orders = new ArrayList<>();

        Employee loggedInEmployee = SessionManager.getInstance().getEmployeeFromSession(request.getSession());
        if (loggedInEmployee != null && loggedInEmployee.getRole() == Role.CEO)
            orders = OrderService.getInstance().getAllOrders();

        Client loggedInClient = SessionManager.getInstance().getClientFromSession(request.getSession());
        if (loggedInClient != null) {
            orders = OrderService.getInstance().getAllOrdersByClient(loggedInClient.getId());
        }

        orders = searchByName(orders, request);
        orders = filterByStatuses(orders, request);
        orders = filterByClients(orders, request);
        orders = filterByDates(orders, request);
        sort(orders, request);

        request.setAttribute(Attribute.ORDERS, orders);
        request.setAttribute(Attribute.CLIENTS, ClientService.getInstance().getAllClients());
        return Page.ALL_ORDERS_VIEW;
    }

    private List<Order> searchByName(List<Order> orders, HttpServletRequest request) {
        String searchName = request.getParameter(Attribute.NAME);
        if (searchName != null && !searchName.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> order.getName().toLowerCase().contains(searchName.toLowerCase()))
                    .toList();
            request.setAttribute(Attribute.NAME, searchName);
        }
        return new ArrayList<>(orders);
    }

    private List<Order> filterByStatuses(List<Order> orders, HttpServletRequest request) {
        String[] statuses = request.getParameterValues(Attribute.STATUSES);
        List<String> statusesList = (statuses == null) ? new ArrayList<>() : List.of(statuses);
        if (!statusesList.isEmpty()) {
            orders = orders.stream()
                    .filter(order -> statusesList.contains(order.getStatus().getValue()))
                    .toList();
            request.setAttribute(Attribute.STATUSES, statusesList);
        }
        return new ArrayList<>(orders);
    }

    private List<Order> filterByClients(List<Order> orders, HttpServletRequest request) {
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
        return new ArrayList<>(orders);
    }

    private List<Order> filterByDates(List<Order> orders, HttpServletRequest request) {
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
                    || order.getDueDate().isBefore(LocalDate.parse(dueTo).plusDays(1).atStartOfDay())))
            .toList();

        if(startFrom != null)
            request.setAttribute(Attribute.START_DATE_FROM, startFrom);
        if(startTo != null)
            request.setAttribute(Attribute.START_DATE_TO, startTo);
        if(dueFrom != null)
            request.setAttribute(Attribute.DUE_DATE_FROM, dueFrom);
        if(dueTo != null)
            request.setAttribute(Attribute.DUE_DATE_TO, dueTo);

        return new ArrayList<>(orders);
    }

    private void sort(List<Order> orders, HttpServletRequest request) {
        String sortBy = request.getParameter(Attribute.SORT_BY);
        String descending = request.getParameter(Attribute.DESCENDING);

        Comparator<Order> comparator = (sortBy == null) ?
                Comparator.comparing(order -> order.getName().toLowerCase()) :
            switch (sortBy) {
                case "creationDate" -> Comparator.comparing(Order::getCreationDate);
                case "cost" -> Comparator.comparing(Order::getCost);
                case "status" -> Comparator.comparing(Order::getStatus);
                default -> Comparator.comparing(order -> order.getName().toLowerCase());
            };

        if (descending != null && descending.equals("on"))
            comparator = comparator.reversed();
        orders.sort(comparator);

        if(sortBy != null)
            request.setAttribute(Attribute.SORT_BY, sortBy);
        if(descending != null)
            request.setAttribute(Attribute.DESCENDING, descending);
    }
}
