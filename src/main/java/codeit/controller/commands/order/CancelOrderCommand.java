package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.models.entities.Order;
import codeit.models.enums.OrderStatus;
import codeit.services.OrderService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CancelOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter(Attribute.ORDER_ID);
        Order order = OrderService.getInstance().getOrderById(orderId);

        if (order.getStatus() == OrderStatus.DONE || order.getStatus() == OrderStatus.REJECTED ||
                order.getStatus() == OrderStatus.CANCELLED) {
            redirectToOrderPageWithErrorMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        OrderService.getInstance().cancelOrder(orderId);
        ProjectService.getInstance().cancelProjectByOrder(orderId);
        TaskService.getInstance().cancelTasksByOrder(orderId);

        redirectToOrderPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToOrderPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ORDER_ID, request.getParameter(Attribute.ORDER_ID));
        urlParams.put(Attribute.SUCCESS, "Order successfully cancelled");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.ORDER, urlParams);
    }

    private void redirectToOrderPageWithErrorMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ORDER_ID, request.getParameter(Attribute.ORDER_ID));
        urlParams.put(Attribute.ERROR, "Order can not be cancelled");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.ORDER, urlParams);
    }
}
