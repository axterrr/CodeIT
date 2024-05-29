package codeit.services;

import codeit.dao.EmployeeDao;
import codeit.dao.DaoFactory;
import codeit.dto.CredentialsDto;
import codeit.models.entities.Employee;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private static final EmployeeService INSTANCE = new EmployeeService(DaoFactory.getDaoFactory());
    private final DaoFactory daoFactory;
    EmployeeService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public static EmployeeService getInstance() {
        return EmployeeService.INSTANCE;
    }

    public List<Employee> getAllEmployees() {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            return dao.getAll();
        }
    }

    public Employee getEmployeeById(String employeeId) {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            return dao.getById(employeeId);
        }
    }

    public void createEmployee(Employee employee) {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            dao.create(employee);
        }
    }

    public void updateEmployee(Employee employee) {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            dao.update(employee);
        }
    }

    public void deleteEmployee(String employeeId) {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            dao.delete(employeeId);
        }
    }

    public Employee getEmployeeByCredentials(CredentialsDto credentials) {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getByCredentials(credentials.getEmail(), credentials.getPassword());
        }
    }
}
