package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddTaskCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        if (projectId != null)
            request.setAttribute(Attribute.PROJECT_ID, projectId);
        request.setAttribute(Attribute.PROJECTS, ProjectService.getInstance().getAllProjects());
        request.setAttribute(Attribute.DEVELOPERS, EmployeeService.getInstance().getAllDevelopers());
        request.setAttribute(Attribute.TESTERS, EmployeeService.getInstance().getAllTesters());
        return Page.ADD_UPDATE_TASK_VIEW;
    }
}
