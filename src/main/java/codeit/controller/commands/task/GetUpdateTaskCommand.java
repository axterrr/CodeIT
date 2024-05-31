package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Task;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUpdateTaskCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String taskId = request.getParameter(Attribute.TASK_ID);
        Task task = TaskService.getInstance().getTaskById(taskId);
        request.setAttribute(Attribute.TASK_DTO, task);
        return Page.ADD_UPDATE_TASK_VIEW;
    }
}
