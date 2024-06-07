package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.services.EmployeeService;
import codeit.services.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(Attribute.ORDERS, OrderService.getInstance().getAllOrdersWithoutProjects());
        request.setAttribute(Attribute.MANAGERS, EmployeeService.getInstance().getAllManagers());
        return Page.ADD_UPDATE_PROJECT_VIEW;
    }
}
