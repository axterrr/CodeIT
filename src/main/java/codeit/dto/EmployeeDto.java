package codeit.dto;

import codeit.models.entities.Employee;
import codeit.models.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class EmployeeDto {

    private String id;
    private String firstName;
    private String lastName;
    private String role;
    private String specialisation;
    private String salary;
    private String email;
    private String phone;
    private String address;
    private String hireDate;
    private String birthDate;
    private String password;
    private String confirmPassword;

    public Employee toEmployee() {

        if (id == null)
            id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        if(hireDate == null)
            hireDate = LocalDateTime.now().toString();

        return new Employee.Builder()
                .setId(id)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setRole(Role.getRole(role))
                .setSpecialisation(specialisation)
                .setSalary(new BigDecimal(salary))
                .setEmail(email)
                .setPhone(phone)
                .setAddress(address)
                .setHireDate(LocalDateTime.parse(hireDate))
                .setBirthDate(LocalDateTime.of(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.MIDNIGHT))
                .setPassword(password)
                .build();
    }

    public static class Builder {
        EmployeeDto employee = new EmployeeDto();

        public Builder setId(String id) {
            employee.id = id;
            return this;
        }

        public Builder setFirstName(String name) {
            employee.firstName = name;
            return this;
        }

        public Builder setLastName(String name) {
            employee.lastName = name;
            return this;
        }

        public Builder setRole(String role) {
            employee.role = role;
            return this;
        }

        public Builder setSpecialisation(String specialisation) {
            employee.specialisation = specialisation;
            return this;
        }

        public Builder setSalary(String salary) {
            employee.salary = salary;
            return this;
        }

        public Builder setEmail(String email) {
            employee.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            employee.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            employee.address = address;
            return this;
        }

        public Builder setHireDate(String hireDate) {
            employee.hireDate = hireDate;
            return this;
        }

        public Builder setBirthDate(String birthDate) {
            employee.birthDate = birthDate;
            return this;
        }

        public Builder setPassword(String password) {
            employee.password = password;
            return this;
        }

        public Builder setConfirmPassword(String password) {
            employee.confirmPassword = password;
            return this;
        }

        public EmployeeDto build() {
            return employee;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRoleString() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHireDate() {
        return hireDate;
    }

    public void setHireDate(String hireDate) {
        this.hireDate = hireDate;
    }

    public String getBirthDateString() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
