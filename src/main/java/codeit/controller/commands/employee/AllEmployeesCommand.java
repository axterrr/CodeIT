package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.constants.ServletPath;
import codeit.controller.commands.Command;
import codeit.controller.utils.RedirectionManager;
import codeit.controller.utils.SessionManager;
import codeit.models.entities.Employee;
import codeit.models.enums.Role;
import codeit.services.ClientService;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class AllEmployeesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Employee> employees = new ArrayList<>();

        Employee loggedInEmployee = SessionManager.getInstance().getEmployeeFromSession(request.getSession());
        if (loggedInEmployee.getRole() == Role.CEO) {
            employees = EmployeeService.getInstance().getAllEmployees();
        }
        if (loggedInEmployee.getRole() == Role.PROJECT_MANAGER) {
            employees.addAll(EmployeeService.getInstance().getAllDevelopers());
            employees.addAll(EmployeeService.getInstance().getAllTesters());
            employees.add(EmployeeService.getInstance().getEmployeeById(loggedInEmployee.getId()));
        }
        if (loggedInEmployee.getRole() == Role.TESTER || loggedInEmployee.getRole() == Role.DEVELOPER) {
            Map<String, String> urlParams = new HashMap<>();
            urlParams.put(Attribute.EMPLOYEE_ID, loggedInEmployee.getId());
            RedirectionManager.getInstance().redirectWithParams(request, response, ServletPath.EMPLOYEE, urlParams);
            return RedirectionManager.REDIRECTION;
        }

        employees = searchByName(employees, request);
        employees = filterByRoles(employees, request);
        sort(employees, request);

        request.setAttribute(Attribute.EMPLOYEES, employees);
        return Page.ALL_EMPLOYEES_VIEW;
    }

    private List<Employee> searchByName(List<Employee> employees, HttpServletRequest request) {
        String searchName = request.getParameter(Attribute.NAME);
        if (searchName != null && !searchName.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> {
                        String emplName = employee.getFirstName().toLowerCase() + ' ' + employee.getLastName().toLowerCase();
                        return emplName.contains(searchName.toLowerCase());
                    })
                    .toList();
            request.setAttribute(Attribute.NAME, searchName);
        }

        return new ArrayList<>(employees);
    }

    private List<Employee> filterByRoles(List<Employee> employees, HttpServletRequest request) {
        String[] roles = request.getParameterValues(Attribute.ROLES);
        List<String> rolesList = (roles == null) ? new ArrayList<>() : List.of(roles);
        if (!rolesList.isEmpty()) {
            employees = employees.stream()
                    .filter(employee -> rolesList.contains(employee.getRole().getValue()))
                    .toList();
            request.setAttribute(Attribute.ROLES, rolesList);
        }
        return new ArrayList<>(employees);
    }

    private void sort(List<Employee> employees, HttpServletRequest request) {
        String sortBy = request.getParameter(Attribute.SORT_BY);
        String descending = request.getParameter(Attribute.DESCENDING);

        Comparator<Employee> comparator = (sortBy == null) ? Comparator.comparing(employee ->
                employee.getLastName().toLowerCase() + ' ' + employee.getLastName().toLowerCase()) :
            switch (sortBy) {
                case "projectsAmount" -> Comparator.comparing(employee -> {
                    if(employee.getRole() != Role.PROJECT_MANAGER)
                        return 0;
                    return ProjectService.getInstance().getAllProjectsByManager(employee.getId()).size();
                });
                case "tasksAmount" -> Comparator.comparing(employee -> {
                    if(employee.getRole() == Role.DEVELOPER)
                        return TaskService.getInstance().getAllTasksByDeveloper(employee.getId()).size();
                    if(employee.getRole() == Role.TESTER)
                        return TaskService.getInstance().getAllTasksByTester(employee.getId()).size();
                    return 0;
                });
                default -> Comparator.comparing(employee ->
                        employee.getLastName().toLowerCase() + ' ' + employee.getLastName().toLowerCase());
            };

        if (descending != null && descending.equals("on"))
            comparator = comparator.reversed();
        employees.sort(comparator);

        if(sortBy != null)
            request.setAttribute(Attribute.SORT_BY, sortBy);
        if(descending != null)
            request.setAttribute(Attribute.DESCENDING, descending);
    }
}
