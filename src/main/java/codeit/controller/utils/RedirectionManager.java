package codeit.controller.utils;

import codeit.exceptions.ServerException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class RedirectionManager {

    public static String REDIRECTION = "REDIRECTION";

    private RedirectionManager() {}

    private static final RedirectionManager INSTANCE = new RedirectionManager();

    public static RedirectionManager getInstance() {
        return INSTANCE;
    }

    public void redirectWithParams(HttpServletRequest request, HttpServletResponse response,
                                   String redirectionPath, Map<String, String> urlParameters) throws IOException {

        String pathWithParams = redirectionPath + generateUrlParams(urlParameters);
        redirect(request, response, pathWithParams);
    }

    public void redirect(HttpServletRequest request, HttpServletResponse response, String path) {
        try {
            response.sendRedirect(request.getContextPath() + request.getServletPath() + path);
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    public String generateUrlParams(Map<String, String> urlParameters) {
        StringBuilder stringBuilder = new StringBuilder("?");
        for (String urlParamName : urlParameters.keySet()) {
            stringBuilder
                    .append(urlParamName)
                    .append("=")
                    .append(URLEncoder.encode(urlParameters.get(urlParamName), StandardCharsets.UTF_8))
                    .append("&");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
