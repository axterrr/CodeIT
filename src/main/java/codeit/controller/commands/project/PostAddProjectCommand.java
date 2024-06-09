package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.dto.ProjectDto;
import codeit.models.enums.OrderStatus;
import codeit.services.EmployeeService;
import codeit.services.OrderService;
import codeit.services.ProjectService;
import codeit.validators.entities.ProjectDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAddProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProjectDto projectDto = getUserInput(request);
        List<String> errors = ProjectDtoValidator.getInstance().validate(projectDto);

        if (errors.isEmpty()) {
            if (OrderService.getInstance().getOrderById(projectDto.getOrderId()).getStatus() == OrderStatus.ACCEPTED) {
                ProjectService.getInstance().createProject(projectDto.toProject());
                OrderService.getInstance().startDevelopingOrder(projectDto.getOrderId());
                redirectToProjectPageWithSuccessMessage(request, response, projectDto.getId());
                return RedirectionManager.REDIRECTION;
            }
            else {
                errors.add("This order can not have new project");
            }
        }

        request.setAttribute(Attribute.ORDERS, OrderService.getInstance().getAllAcceptedOrders());
        request.setAttribute(Attribute.MANAGERS, EmployeeService.getInstance().getAllManagers());
        addRequestAttributes(request, projectDto, errors);
        return Page.ADD_UPDATE_PROJECT_VIEW;
    }

    private ProjectDto getUserInput(HttpServletRequest request) {
        return new ProjectDto.Builder()
                .setOrder(request.getParameter(Attribute.ORDER_ID))
                .setManager(request.getParameter(Attribute.MANAGER_ID))
                .setName(request.getParameter(Attribute.NAME))
                .setDescription(request.getParameter(Attribute.DESCRIPTION))
                .setGitHubLink(request.getParameter(Attribute.PROJECT_LINK))
                .setBudget(request.getParameter(Attribute.BUDGET))
                .setDueDate(request.getParameter(Attribute.DUE_DATE))
                .setStatus(request.getParameter(Attribute.STATUS))
                .build();
    }

    private void redirectToProjectPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response,
                                                       String projectId) throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.PROJECT_ID, projectId);
        urlParams.put(Attribute.SUCCESS, "Project successfully added");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.PROJECT, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, ProjectDto projectDto, List<String> errors) {
        request.setAttribute(Attribute.PROJECT_DTO, projectDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
