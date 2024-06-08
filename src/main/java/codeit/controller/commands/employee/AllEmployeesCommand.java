package codeit.controller.commands.employee;

import codeit.constants.Attribute;
import codeit.constants.Page;
import codeit.controller.commands.Command;
import codeit.models.entities.Employee;
import codeit.models.enums.Role;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;
import codeit.services.TaskService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AllEmployeesCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Employee> employees = EmployeeService.getInstance().getAllEmployees();

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
