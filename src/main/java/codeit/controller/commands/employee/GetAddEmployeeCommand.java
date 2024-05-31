package codeit.controller.commands.employee;

import codeit.constants.Page;
import codeit.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAddEmployeeCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return Page.ADD_UPDATE_EMPLOYEE_VIEW;
    }
}
