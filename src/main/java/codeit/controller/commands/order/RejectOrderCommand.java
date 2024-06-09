package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RejectOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String orderId = request.getParameter(Attribute.ORDER_ID);
        OrderService.getInstance().rejectOrder(orderId);
        redirectToOrderPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToOrderPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ORDER_ID, request.getParameter(Attribute.ORDER_ID));
        urlParams.put(Attribute.SUCCESS, "Order successfully rejected");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.ORDER, urlParams);
    }
}
