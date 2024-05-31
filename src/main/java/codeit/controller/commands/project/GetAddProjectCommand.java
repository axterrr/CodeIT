package codeit.controller.commands.project;

import codeit.constants.Page;
import codeit.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddProjectCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_UPDATE_PROJECT_VIEW;
    }
}
