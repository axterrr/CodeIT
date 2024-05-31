package codeit.controller.commands.order;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.dto.OrderDto;
import codeit.services.OrderService;
import codeit.validators.entities.OrderDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderDto orderDto = getUserInput(request);
        List<String> errors = OrderDtoValidator.getInstance().validate(orderDto);

        if (errors.isEmpty()) {
            OrderService.getInstance().updateOrder(orderDto.toOrder());
            redirectToAllOrdersPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        addRequestAttributes(request, orderDto, errors);
        return Page.ADD_UPDATE_ORDER_VIEW;
    }

    private OrderDto getUserInput(HttpServletRequest request) {
        return new OrderDto.Builder()
                .setId(request.getParameter(Attribute.ORDER_ID))
                .setClient(request.getParameter(Attribute.CLIENT_ID))
                .setName(request.getParameter(Attribute.NAME))
                .setDescription(request.getParameter(Attribute.DESCRIPTION))
                .setDueDate(request.getParameter(Attribute.DUE_DATE))
                .setCost(request.getParameter(Attribute.COST))
                .setStatus(request.getParameter(Attribute.STATUS))
                .build();
    }

    private void redirectToAllOrdersPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Order successfully updated");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.ORDERS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, OrderDto orderDto, List<String> errors) {
        request.setAttribute(Attribute.ORDER_DTO, orderDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
