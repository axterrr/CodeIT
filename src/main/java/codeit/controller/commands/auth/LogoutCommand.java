package codeit.controller.commands.auth;

import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.controller.utils.SessionManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        SessionManager.getInstance().invalidateSession(request.getSession());
        RedirectionManager.getInstance().redirect(request, response, ServletPath.HOME);
        return RedirectionManager.REDIRECTION;
    }
}
