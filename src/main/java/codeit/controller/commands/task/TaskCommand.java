package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Task;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TaskCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String taskId = request.getParameter(Attribute.TASK_ID);
        request.setAttribute(Attribute.TASK, TaskService.getInstance().getTaskById(taskId));
        return Page.TASK_VIEW;
    }
}

