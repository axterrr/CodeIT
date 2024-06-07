package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        request.setAttribute(Attribute.PROJECT, ProjectService.getInstance().getProjectById(projectId));
        request.setAttribute(Attribute.TASKS, TaskService.getInstance().getAllTasksByProject(projectId));
        return Page.PROJECT_VIEW;
    }
}
