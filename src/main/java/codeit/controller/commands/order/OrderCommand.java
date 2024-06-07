package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.services.OrderService;
import codeit.services.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter(Attribute.ORDER_ID);
        request.setAttribute(Attribute.ORDER, OrderService.getInstance().getOrderById(orderId));
        request.setAttribute(Attribute.PROJECT, ProjectService.getInstance().getProjectByOrder(orderId));
        return Page.ORDER_VIEW;
    }
}

