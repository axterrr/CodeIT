package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.models.entities.Task;
import codeit.models.enums.TaskStatus;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RejectTaskTestCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taskId = request.getParameter(Attribute.TASK_ID);
        Task task = TaskService.getInstance().getTaskById(taskId);

        if (task.getStatus() != TaskStatus.TESTING) {
            redirectToTaskPageWithErrorMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        TaskService.getInstance().rejectTask(taskId);
        redirectToTaskPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToTaskPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.TASK_ID, request.getParameter(Attribute.TASK_ID));
        urlParams.put(Attribute.SUCCESS, "Task successfully rejected");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.TASK, urlParams);
    }

    private void redirectToTaskPageWithErrorMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.TASK_ID, request.getParameter(Attribute.TASK_ID));
        urlParams.put(Attribute.ERROR, "Task can not be rejected");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.TASK, urlParams);
    }
}
