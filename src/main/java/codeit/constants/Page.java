package codeit.constants;

public class Page {

    public static final String PREFIX = "/WEB-INF/views/";
    public static final String ERROR_PREFIX = "errors/";
    public static final String SUFFIX = ".jsp";


    public static final String HOME_VIEW = "/index" + SUFFIX;
    public static final String LOGIN_VIEW = PREFIX + "login" + SUFFIX;
    public static final String PAGE_NOT_FOUND = PREFIX + ERROR_PREFIX + "pageNotFound" + SUFFIX;

    public static final String ALL_CLIENTS_VIEW = PREFIX + "allClients" + SUFFIX;
    public static final String ALL_ORDERS_VIEW = PREFIX + "allOrders" + SUFFIX;
    public static final String ALL_PROJECTS_VIEW = PREFIX + "allProjects" + SUFFIX;
    public static final String ALL_EMPLOYEES_VIEW = PREFIX + "allEmployees" + SUFFIX;
    public static final String ALL_TASKS_VIEW = PREFIX + "allTasks" + SUFFIX;

    public static final String ADD_UPDATE_CLIENT_VIEW = PREFIX + "addUpdateClient" + SUFFIX;
    public static final String ADD_UPDATE_ORDER_VIEW = PREFIX + "addUpdateOrder" + SUFFIX;
    public static final String ADD_UPDATE_PROJECT_VIEW = PREFIX + "addUpdateProject" + SUFFIX;
    public static final String ADD_UPDATE_EMPLOYEE_VIEW = PREFIX + "addUpdateEmployee" + SUFFIX;
    public static final String ADD_UPDATE_TASK_VIEW = PREFIX + "addUpdateTask" + SUFFIX;
}
