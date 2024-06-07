package codeit.controller.commands.task;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.dto.TaskDto;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;
import codeit.validators.entities.TaskDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostAddTaskCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        TaskDto taskDto = getUserInput(request);
        List<String> errors = TaskDtoValidator.getInstance().validate(taskDto);

        if (errors.isEmpty()) {
            TaskService.getInstance().createTask(taskDto.toTask());
            redirectToAllTasksPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.PROJECTS, ProjectService.getInstance().getAllProjects());
        request.setAttribute(Attribute.DEVELOPERS, EmployeeService.getInstance().getAllDevelopers());
        request.setAttribute(Attribute.TESTERS, EmployeeService.getInstance().getAllTesters());
        addRequestAttributes(request, taskDto, errors);
        return Page.ADD_UPDATE_TASK_VIEW;
    }

    private TaskDto getUserInput(HttpServletRequest request) {
        return new TaskDto.Builder()
                .setProject(request.getParameter(Attribute.PROJECT_ID))
                .setDeveloper(request.getParameter(Attribute.DEVELOPER_ID))
                .setTester(request.getParameter(Attribute.TESTER_ID))
                .setName(request.getParameter(Attribute.NAME))
                .setDescription(request.getParameter(Attribute.DESCRIPTION))
                .setBranchLink(request.getParameter(Attribute.BRANCH_LINK))
                .setDueDate(request.getParameter(Attribute.DUE_DATE))
                .setStatus(request.getParameter(Attribute.STATUS))
                .setComment(request.getParameter(Attribute.COMMENT))
                .build();
    }

    private void redirectToAllTasksPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Task successfully added");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.TASKS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, TaskDto taskDto, List<String> errors) {
        request.setAttribute(Attribute.TASK_DTO, taskDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
