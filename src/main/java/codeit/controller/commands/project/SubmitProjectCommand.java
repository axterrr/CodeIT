package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.models.entities.Project;
import codeit.models.entities.Task;
import codeit.models.enums.ProjectStatus;
import codeit.models.enums.TaskStatus;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubmitProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String projectId = request.getParameter(Attribute.PROJECT_ID);
        Project project = ProjectService.getInstance().getProjectById(projectId);

        if (project.getStatus() != ProjectStatus.DEVELOPING || !allTasksAreFinished(project)) {
            redirectToProjectPageWithErrorMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        ProjectService.getInstance().submitProject(projectId);
        redirectToProjectPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private boolean allTasksAreFinished(Project project) {
        for(Task task : TaskService.getInstance().getAllTasksByProject(project.getId())) {
            if(task.getStatus() != TaskStatus.FINISHED)
                return false;
        }
        return true;
    }

    private void redirectToProjectPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.PROJECT_ID, request.getParameter(Attribute.PROJECT_ID));
        urlParams.put(Attribute.SUCCESS, "Project successfully submitted");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.PROJECT, urlParams);
    }

    private void redirectToProjectPageWithErrorMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.PROJECT_ID, request.getParameter(Attribute.PROJECT_ID));
        urlParams.put(Attribute.ERROR, "Project can not be submitted");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.PROJECT, urlParams);
    }
}
