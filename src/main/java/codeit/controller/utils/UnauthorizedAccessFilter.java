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

@WebFilter(urlPatterns = { "/controller/*" })
public class UnauthorizedAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        if (httpRequest.getRequestURI().equals("/controller/")
                || httpRequest.getRequestURI().equals("/controller/login")) {
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
        return requestURI.equals("/controller/login")
                || requestURI.equals("/controller/logout")
                || requestURI.equals("/controller/clients")
                || requestURI.equals("/controller/clients/client")
                || requestURI.equals("/controller/clients/update")
                || requestURI.equals("/controller/orders")
                || requestURI.equals("/controller/orders/order")
                || requestURI.equals("/controller/orders/add")
                || requestURI.equals("/controller/orders/cancel");
    }

    private boolean isCeoPage(String requestURI) {
        return !requestURI.equals("/controller/orders/add")
                && !requestURI.equals("/controller/orders/cancel")
                && !requestURI.equals("/controller/projects/submit")
                && !requestURI.equals("/controller/tasks/add")
                && !requestURI.equals("/controller/tasks/update")
                && !requestURI.equals("/controller/tasks/delete")
                && !requestURI.equals("/controller/tasks/submit")
                && !requestURI.equals("/controller/tasks/confirm")
                && !requestURI.equals("/controller/tasks/reject")
                && !requestURI.equals("/controller/tasks/confirmTest")
                && !requestURI.equals("/controller/tasks/rejectTest")
                && !requestURI.equals("/controller/tasks/comment");
    }

    private boolean isProjectManagerPage(String requestURI) {
        return requestURI.equals("/controller/login")
                || requestURI.equals("/controller/logout")
                || requestURI.equals("/controller/employees")
                || requestURI.equals("/controller/employees/employee")
                || requestURI.equals("/controller/projects")
                || requestURI.equals("/controller/projects/project")
                || requestURI.equals("/controller/projects/submit")
                || requestURI.equals("/controller/tasks")
                || requestURI.equals("/controller/tasks/task")
                || requestURI.equals("/controller/tasks/add")
                || requestURI.equals("/controller/tasks/update")
                || requestURI.equals("/controller/tasks/delete")
                || requestURI.equals("/controller/tasks/confirm")
                || requestURI.equals("/controller/tasks/reject")
                || requestURI.equals("/controller/tasks/submit");
    }

    private boolean isDeveloperPage(String requestURI) {
        return requestURI.equals("/controller/login")
                || requestURI.equals("/controller/logout")
                || requestURI.equals("/controller/employees")
                || requestURI.equals("/controller/employees/employee")
                || requestURI.equals("/controller/projects")
                || requestURI.equals("/controller/projects/project")
                || requestURI.equals("/controller/tasks")
                || requestURI.equals("/controller/tasks/task")
                || requestURI.equals("/controller/tasks/submit");
    }

    private boolean isTesterPage(String requestURI) {
        return requestURI.equals("/controller/login")
                || requestURI.equals("/controller/logout")
                || requestURI.equals("/controller/employees")
                || requestURI.equals("/controller/employees/employee")
                || requestURI.equals("/controller/projects")
                || requestURI.equals("/controller/projects/project")
                || requestURI.equals("/controller/tasks")
                || requestURI.equals("/controller/tasks/task")
                || requestURI.equals("/controller/tasks/confirmTest")
                || requestURI.equals("/controller/tasks/rejectTest")
                || requestURI.equals("/controller/tasks/comment");
    }
}
