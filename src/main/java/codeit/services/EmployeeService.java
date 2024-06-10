package codeit.services;

import codeit.dao.EmployeeDao;
import codeit.dao.DaoFactory;
import codeit.dto.CredentialsDto;
import codeit.models.entities.Employee;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
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

            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(employee.getPassword().toCharArray(), salt, iterations, keyLength);
            employee.setPassword(hashedPassword);

            dao.create(employee);
        }
    }

    public void updateEmployee(Employee employee) {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {

            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(employee.getPassword().toCharArray(), salt, iterations, keyLength);
            employee.setPassword(hashedPassword);

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

            byte[] salt = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            int iterations = 10000;
            int keyLength = 256;

            String hashedPassword = hashPassword(credentials.getPassword().toCharArray(), salt, iterations, keyLength);

            return employeeDao.getByCredentials(credentials.getEmail(), hashedPassword);
        }
    }

    public Employee getEmployeeByEmail(String email) {
        try (EmployeeDao employeeDao = daoFactory.createEmployeeDao()) {
            return employeeDao.getByEmail(email);
        }
    }

    public List<Employee> getAllManagers() {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            return dao.getManagers();
        }
    }

    public List<Employee> getAllDevelopers() {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            return dao.getDevelopers();
        }
    }

    public List<Employee> getAllTesters() {
        try (EmployeeDao dao = daoFactory.createEmployeeDao()) {
            return dao.getTesters();
        }
    }

    private static String hashPassword(final char[] password, final byte[] salt, final int iterations, final int keyLength) {
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec spec = new PBEKeySpec(password, salt, iterations, keyLength);
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing a password", e);
        }
    }
}
