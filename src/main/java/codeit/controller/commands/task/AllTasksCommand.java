package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Task;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AllTasksCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Task> tasks = TaskService.getInstance().getAllTasks();
        request.setAttribute(Attribute.TASKS, tasks);
        return Page.ALL_TASKS_VIEW;
    }
}
