package codeit.controller.utils;

import codeit.constants.Attribute;
import codeit.constants.ServletPath;
import codeit.models.entities.Client;
import codeit.models.entities.Employee;
import codeit.models.enums.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebFilter(urlPatterns = { "/*" })
public class UnauthorizedAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getRequestURI().equals("/") || httpRequest.getRequestURI().equals("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Employee employee = SessionManager.getInstance().getEmployeeFromSession(httpRequest.getSession());
        if (employee!=null && isUserAuthorizedForResource(httpRequest.getRequestURI(), employee)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Client client = SessionManager.getInstance().getClientFromSession(httpRequest.getSession());
        if (client!=null && isUserAuthorizedForResource(httpRequest.getRequestURI(), client)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(Attribute.ERROR, "Unauthorized access to the resource");
        RedirectionManager.getInstance().redirectWithParams(httpRequest, httpResponse, ServletPath.HOME, urlParams);
    }

    @Override
    public void destroy() {}

    private boolean isUserAuthorizedForResource(String servletPath, Employee user) {
        return (user.getRole().equals(Role.CEO) && isCeoPage(servletPath))
                || (user.getRole().equals(Role.PROJECT_MANAGER) && isProjectManagerPage(servletPath))
                || (user.getRole().equals(Role.DEVELOPER) && isDeveloperPage(servletPath))
                || (user.getRole().equals(Role.TESTER) && isTesterPage(servletPath));
    }

    private boolean isUserAuthorizedForResource(String servletPath, Client client) {
        return isClientPage(servletPath);
    }

    private boolean isClientPage(String requestURI) {
        return true;
    }

    private boolean isCeoPage(String requestURI) {
        return true;
    }

    private boolean isProjectManagerPage(String requestURI) {
        return true;
    }

    private boolean isDeveloperPage(String requestURI) {
        return true;
    }

    private boolean isTesterPage(String requestURI) {
        return true;
    }
}
