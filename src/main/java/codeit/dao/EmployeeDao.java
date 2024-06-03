package codeit.dao;

import codeit.exceptions.ServerException;
import codeit.models.entities.Employee;
import codeit.models.enums.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements AutoCloseable {

    private static String GET_ALL = "SELECT * FROM `employee`";
    private static String GET_BY_ID = "SELECT * FROM `employee` WHERE employee_id=?";
    private static String CREATE =
            "INSERT INTO `employee` " +
            "(employee_id, first_name, last_name, role, specialisation, salary, email, phone_number, address, hire_date, birth_date, password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE =
            "UPDATE `employee` " +
            "SET first_name=?, last_name=?, role=?, specialisation=?, salary=?, email=?, phone_number=?, address=?, birth_date=?, password=? " +
            "WHERE employee_id=?";
    private static String DELETE = "DELETE FROM `employee` WHERE employee_id=?";
    private static String GET_BY_CREDENTIALS = "SELECT * FROM `employee` WHERE email=? AND password=?";

    private static String ID = "employee_id";
    private static String FIRST_NAME = "first_name";
    private static String LAST_NAME = "last_name";
    private static String ROLE = "role";
    private static String SPECIALISATION = "specialisation";
    private static String SALARY = "salary";
    private static String EMAIL = "email";
    private static String PHONE = "phone_number";
    private static String ADDRESS = "address";
    private static String HIRE_DATE = "hire_date";
    private static String BIRTH_DATE = "birth_date";
    private static String PASSWORD = "password";

    private Connection connection;

    public EmployeeDao(Connection connection) {
        this.connection = connection;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                employees.add(extractEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employees;
    }

    public Employee getById(String id) {
        Employee employee = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = extractEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employee;
    }

    public void create(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, employee.getId());
            query.setString(2, employee.getFirstName());
            query.setString(3, employee.getLastName());
            query.setString(4, employee.getRole().getValue());
            query.setString(5, employee.getSpecialisation());
            query.setBigDecimal(6, employee.getSalary());
            query.setString(7, employee.getEmail());
            query.setString(8, employee.getPhone());
            query.setString(9, employee.getAddress());
            query.setTimestamp(10, Timestamp.valueOf(employee.getHireDate()));
            query.setTimestamp(11, Timestamp.valueOf(employee.getBirthDate()));
            query.setString(12, employee.getPassword());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void update(Employee employee) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, employee.getFirstName());
            query.setString(2, employee.getLastName());
            query.setString(3, employee.getRole().getValue());
            query.setString(4, employee.getSpecialisation());
            query.setBigDecimal(5, employee.getSalary());
            query.setString(6, employee.getEmail());
            query.setString(7, employee.getPhone());
            query.setString(8, employee.getAddress());
            query.setTimestamp(9, Timestamp.valueOf(employee.getBirthDate()));
            query.setString(10, employee.getPassword());
            query.setString(11, employee.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public Employee getByCredentials(String email, String password) {
        Employee employee = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CREDENTIALS)) {
            query.setString(1, email);
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                employee = extractEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return employee;
    }

    private static Employee extractEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        return new Employee.Builder()
                .setId(resultSet.getString(ID))
                .setFirstName(resultSet.getString(FIRST_NAME))
                .setLastName(resultSet.getString(LAST_NAME))
                .setRole(Role.getRole(resultSet.getString(ROLE)))
                .setSpecialisation(resultSet.getString(SPECIALISATION))
                .setSalary(resultSet.getBigDecimal(SALARY))
                .setEmail(resultSet.getString(EMAIL))
                .setPhone(resultSet.getString(PHONE))
                .setAddress(resultSet.getString(ADDRESS))
                .setHireDate(resultSet.getTimestamp(HIRE_DATE).toLocalDateTime())
                .setBirthDate(resultSet.getTimestamp(BIRTH_DATE).toLocalDateTime())
                .setPassword(resultSet.getString(PASSWORD))
                .build();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}
