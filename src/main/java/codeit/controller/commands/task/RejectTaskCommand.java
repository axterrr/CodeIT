package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RejectTaskCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String taskId = request.getParameter(Attribute.TASK_ID);
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
}
