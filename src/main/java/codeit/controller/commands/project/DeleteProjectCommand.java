package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.services.ProjectService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        ProjectService.getInstance().deleteProject(projectId);
        redirectToAllProjectsPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllProjectsPageWithSuccessMessage(HttpServletRequest request,
                                                            HttpServletResponse response) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Project successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.PROJECTS, urlParams);
    }
}
