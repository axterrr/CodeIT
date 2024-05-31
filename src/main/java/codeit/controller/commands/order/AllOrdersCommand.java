package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Order;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Order> orders = OrderService.getInstance().getAllOrders();
        request.setAttribute(Attribute.ORDERS, orders);
        return Page.ALL_ORDERS_VIEW;
    }
}
