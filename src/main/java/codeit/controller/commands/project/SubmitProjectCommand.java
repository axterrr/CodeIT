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

public class SubmitProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        ProjectService.getInstance().submitProject(projectId);
        redirectToProjectPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToProjectPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.PROJECT_ID, request.getParameter(Attribute.PROJECT_ID));
        urlParams.put(Attribute.SUCCESS, "Project successfully submitted");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.PROJECT, urlParams);
    }
}
