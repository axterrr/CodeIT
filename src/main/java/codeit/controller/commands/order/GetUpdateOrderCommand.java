package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Order;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter(Attribute.ORDER_ID);
        Order order = OrderService.getInstance().getOrderById(orderId);
        request.setAttribute(Attribute.ORDER_DTO, order);
        return Page.ADD_UPDATE_ORDER_VIEW;
    }
}
