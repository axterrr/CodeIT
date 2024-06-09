package codeit.controller;

import codeit.controller.commands.*;
import codeit.controller.commands.auth.*;
import codeit.controller.commands.client.*;
import codeit.controller.commands.employee.*;
import codeit.controller.commands.order.*;
import codeit.controller.commands.project.*;
import codeit.controller.commands.task.*;

public enum CommandEnum {

    HOME ("GET:/", new HomeCommand()),
    PAGE_NOT_FOUND ("GET:/pageNotFound", new PageNotFoundCommand()),

    GET_LOGIN ("GET:/login", new GetLoginCommand()),
    LOGOUT ("GET:/logout", new LogoutCommand()),
    POST_LOGIN ("POST:/login", new PostLoginCommand()),

    ALL_CLIENTS ("GET:/clients", new AllClientsCommand()),
    CLIENT ("GET:/clients/client", new ClientCommand()),
    GET_ADD_CLIENT ("GET:/clients/add", new GetAddClientCommand()),
    POST_ADD_CLIENT ("POST:/clients/add", new PostAddClientCommand()),
    GET_UPDATE_CLIENT ("GET:/clients/update", new GetUpdateClientCommand()),
    POST_UPDATE_CLIENT ("POST:/clients/update", new PostUpdateClientCommand()),
    DELETE_CLIENT ("GET:/clients/delete", new DeleteClientCommand()),

    ALL_EMPLOYEES ("GET:/employees", new AllEmployeesCommand()),
    EMPLOYEE ("GET:/employees/employee", new EmployeeCommand()),
    GET_ADD_EMPLOYEE ("GET:/employees/add", new GetAddEmployeeCommand()),
    POST_ADD_EMPLOYEE ("POST:/employees/add", new PostAddEmployeeCommand()),
    GET_UPDATE_EMPLOYEE ("GET:/employees/update", new GetUpdateEmployeeCommand()),
    POST_UPDATE_EMPLOYEE ("POST:/employees/update", new PostUpdateEmployeeCommand()),
    DELETE_EMPLOYEE ("GET:/employees/delete", new DeleteEmployeeCommand()),

    ALL_ORDERS ("GET:/orders", new AllOrdersCommand()),
    ORDER ("GET:/orders/order", new OrderCommand()),
    GET_ADD_ORDER ("GET:/orders/add", new GetAddOrderCommand()),
    POST_ADD_ORDER ("POST:/orders/add", new PostAddOrderCommand()),
    GET_UPDATE_ORDER ("GET:/orders/update", new GetUpdateOrderCommand()),
    POST_UPDATE_ORDER ("POST:/orders/update", new PostUpdateOrderCommand()),
    DELETE_ORDER ("GET:/orders/delete", new DeleteOrderCommand()),
    ACCEPT_ORDER ("GET:/orders/accept", new AcceptOrderCommand()),
    REJECT_ORDER ("GET:/orders/reject", new RejectOrderCommand()),
    CANCEL_ORDER ("GET:/orders/cancel", new CancelOrderCommand()),

    ALL_PROJECTS ("GET:/projects", new AllProjectsCommand()),
    PROJECT ("GET:/projects/project", new ProjectCommand()),
    GET_ADD_PROJECT ("GET:/projects/add", new GetAddProjectCommand()),
    POST_ADD_PROJECT ("POST:/projects/add", new PostAddProjectCommand()),
    GET_UPDATE_PROJECT ("GET:/projects/update", new GetUpdateProjectCommand()),
    POST_UPDATE_PROJECT ("POST:/projects/update", new PostUpdateProjectCommand()),
    DELETE_PROJECT ("GET:/projects/delete", new DeleteProjectCommand()),
    CONFIRM_PROJECT ("GET:/projects/confirm", new ConfirmProjectCommand()),
    REJECT_PROJECT ("GET:/projects/reject", new RejectProjectCommand()),
    SUBMIT_PROJECT ("GET:/projects/submit", new SubmitProjectCommand()),

    ALL_TASKS ("GET:/tasks", new AllTasksCommand()),
    TASK ("GET:/tasks/task", new TaskCommand()),
    GET_ADD_TASK ("GET:/tasks/add", new GetAddTaskCommand()),
    POST_ADD_TASK ("POST:/tasks/add", new PostAddTaskCommand()),
    GET_UPDATE_TASK ("GET:/tasks/update", new GetUpdateTaskCommand()),
    POST_UPDATE_TASK ("POST:/tasks/update", new PostUpdateTaskCommand()),
    DELETE_TASK ("GET:/tasks/delete", new DeleteTaskCommand()),
    SUBMIT_TASK ("GET:/tasks/submit", new SubmitTaskCommand()),
    CONFIRM_TASK ("GET:/tasks/confirm", new ConfirmTaskCommand()),
    CONFIRM_TASK_TEST("GET:/tasks/confirmTest", new ConfirmTaskTestCommand()),
    REJECTS_TASK ("GET:/tasks/reject", new RejectTaskCommand()),
    REJECTS_TASK_TEST ("GET:/tasks/rejectTest", new RejectTaskTestCommand());

    private String key;
    private Command command;

    CommandEnum(String key, Command command) {
        this.key = key;
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }

    public String getKey() {
        return key;
    }

    public static Command getCommand(String key) {
        for (CommandEnum command : CommandEnum.values()) {
            if (command.getKey().equals(key)) {
                return command.getCommand();
            }
        }
        return PAGE_NOT_FOUND.getCommand();
    }
}
