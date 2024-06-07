package codeit.controller.commands.project;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.dto.ProjectDto;
import codeit.models.entities.Project;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.validators.entities.ProjectDtoValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostUpdateProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ProjectDto projectDto = getUserInput(request);
        List<String> errors = ProjectDtoValidator.getInstance().validate(projectDto);

        if (errors.isEmpty()) {
            ProjectService.getInstance().updateProject(projectDto.toProject());
            redirectToAllProjectsPageWithSuccessMessage(request, response);
            return RedirectionManager.REDIRECTION;
        }

        request.setAttribute(Attribute.MANAGERS, EmployeeService.getInstance().getAllManagers());
        addRequestAttributes(request, projectDto, errors);
        return Page.ADD_UPDATE_PROJECT_VIEW;
    }

    private ProjectDto getUserInput(HttpServletRequest request) {
        return new ProjectDto.Builder()
                .setId(request.getParameter(Attribute.PROJECT_ID))
                .setOrder(request.getParameter(Attribute.ORDER_ID))
                .setManager(request.getParameter(Attribute.MANAGER_ID))
                .setName(request.getParameter(Attribute.NAME))
                .setDescription(request.getParameter(Attribute.DESCRIPTION))
                .setGitHubLink(request.getParameter(Attribute.PROJECT_LINK))
                .setBudget(request.getParameter(Attribute.BUDGET))
                .setDueDate(request.getParameter(Attribute.DUE_DATE))
                .setEndDate(request.getParameter(Attribute.END_DATE))
                .setStatus(request.getParameter(Attribute.STATUS))
                .build();
    }

    private void redirectToAllProjectsPageWithSuccessMessage(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Project successfully updated");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.PROJECTS, urlParams);
    }

    private void addRequestAttributes(HttpServletRequest request, ProjectDto projectDto, List<String> errors) {
        request.setAttribute(Attribute.PROJECT_DTO, projectDto);
        request.setAttribute(Attribute.ERRORS, errors);
    }
}
