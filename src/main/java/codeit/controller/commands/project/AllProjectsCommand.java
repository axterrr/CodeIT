package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Project;
import codeit.services.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllProjectsCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Project> projects = ProjectService.getInstance().getAllProjects();
        request.setAttribute(Attribute.PROJECTS, projects);
        return Page.ALL_PROJECTS_VIEW;
    }
}
