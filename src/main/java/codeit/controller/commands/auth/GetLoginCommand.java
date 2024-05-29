package codeit.controller.commands.auth;

import codeit.constants.Page;
import codeit.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetLoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.LOGIN_VIEW;
    }
}
