package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Project;
import codeit.services.EmployeeService;
import codeit.services.OrderService;
import codeit.services.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        Project project = ProjectService.getInstance().getProjectById(projectId);
        request.setAttribute(Attribute.PROJECT_DTO, project);
        request.setAttribute(Attribute.MANAGERS, EmployeeService.getInstance().getAllManagers());
        return Page.ADD_UPDATE_PROJECT_VIEW;
    }
}
