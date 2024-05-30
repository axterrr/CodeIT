package codeit.controller.commands.client;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.services.ClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DeleteClientCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String clientId = request.getParameter(Attribute.CLIENT_ID);
        ClientService.getInstance().deleteClient(clientId);
        redirectToAllClientsPageWithSuccessMessage(request, response);
        return RedirectionManager.REDIRECTION;
    }

    private void redirectToAllClientsPageWithSuccessMessage(HttpServletRequest request,
                                                            HttpServletResponse response) throws IOException {
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.SUCCESS, "Client successfully deleted");
        RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.CLIENTS, urlParams);
    }
}
