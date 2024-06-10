package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.models.entities.Task;
import codeit.models.enums.TaskStatus;
import codeit.services.TaskService;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostTaskCommentCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String comment = request.getParameter(Attribute.COMMENT);
        List<String> errors = new ArrayList<>();
        AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();
        fieldValidator.validateField(FieldValidatorKey.COMMENT, comment, errors);

        if (!errors.isEmpty()) {
            redirectToTaskPageWithErrorMessage(request, response, errors.get(0));
            return RedirectionManager.REDIRECTION;
        }

        String taskId = request.getParameter(Attribute.TASK_ID);
        Task task = TaskService.getInstance().getTaskById(taskId);

        if (task.getStatus() != TaskStatus.TESTING) {
            redirectToTaskPageWithErrorMessage(request, response, "Comment can not be updated");
            return RedirectionManager.REDIRECTION;
        }

        TaskService.getInstance().updateTaskComment(taskId, comment);
        redirectToTaskPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToTaskPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.TASK_ID, request.getParameter(Attribute.TASK_ID));
        urlParams.put(Attribute.SUCCESS, "Comment successfully updated");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.TASK, urlParams);
    }

    private void redirectToTaskPageWithErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                                      String message) throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.TASK_ID, request.getParameter(Attribute.TASK_ID));
        urlParams.put(Attribute.ERROR, message);
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.TASK, urlParams);
    }
}
