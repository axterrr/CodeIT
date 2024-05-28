package codeit.controller;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.exceptions.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = { "/*" }, loadOnStartup = 1)
public class FrontController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String commandKey = request.getMethod().toUpperCase() + ':' + request.getRequestURI();
        Command command = CommandEnum.getCommand(commandKey);

        try {
            String commandResultedResource = command.execute(request, response);
            forwardToCommandResultedPage(request, response, commandResultedResource);
        } catch (ServiceException ex) {
            redirectToHomePageWithErrorMessage(request, response, ex);
        }
    }

    private void forwardToCommandResultedPage(HttpServletRequest request, HttpServletResponse response,
                                              String resultedResource) throws ServletException, IOException {

        if (!resultedResource.contains(RedirectionManager.REDIRECTION))
            request.getRequestDispatcher(resultedResource).forward(request, response);
    }

    private void redirectToHomePageWithErrorMessage(HttpServletRequest request, HttpServletResponse response,
                                                    ServiceException ex) throws IOException {

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ERROR, ex.getMessage());
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.HOME, urlParams);
    }
}
