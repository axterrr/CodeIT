package codeit.controller.utils;

import codeit.constants.Attribute;
import codeit.models.entities.Client;
import codeit.models.entities.Employee;

import javax.servlet.http.HttpSession;

public class SessionManager {

    private SessionManager() {}

    private static final SessionManager INSTANCE = new SessionManager();

    public static SessionManager getInstance() {
        return INSTANCE;
    }

    public boolean isUserLoggedIn(HttpSession session) {
        return session.getAttribute(Attribute.EMPLOYEE) != null
                || session.getAttribute(Attribute.CLIENT) != null;
    }

    public void addEmployeeToSession(HttpSession session, Employee user) {
        session.setAttribute(Attribute.EMPLOYEE, user);
    }

    public void addClientToSession(HttpSession session, Client user) {
        session.setAttribute(Attribute.CLIENT, user);
    }

    public Employee getEmployeeFromSession(HttpSession session) {
        return (Employee) session.getAttribute(Attribute.EMPLOYEE);
    }

    public Client getClientFromSession(HttpSession session) {
        return (Client) session.getAttribute(Attribute.CLIENT);
    }

    public void invalidateSession(HttpSession session) {
        if (session != null && isUserLoggedIn(session))
            session.invalidate();
    }
}
