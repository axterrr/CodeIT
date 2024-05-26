package codeit.models.entities;

import codeit.models.enums.Role;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Employee {
    private String id;
    private String firstName;
    private String lastName;
    private Role role;
    private String specialisation;
    private BigDecimal salary;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime hireDate;
    private LocalDateTime birthDate;
    private String password;

    public static class Builder {

        Employee employee = new Employee();

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

        public Builder setRole(Role role) {
            employee.role = role;
            return this;
        }

        public Builder setSpecialisation(String specialisation) {
            employee.specialisation = specialisation;
            return this;
        }

        public Builder setSalary(BigDecimal salary) {
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

        public Builder setHireDate(LocalDateTime hireDate) {
            employee.hireDate = hireDate;
            return this;
        }

        public Builder setBirthDate(LocalDateTime birthDate) {
            employee.birthDate = birthDate;
            return this;
        }

        public Builder setPassword(String password) {
            employee.password = password;
            return this;
        }

        public Employee build() {
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
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

    public LocalDateTime getHireDate() {
        return hireDate;
    }

    public void setHireDate(LocalDateTime hireDate) {
        this.hireDate = hireDate;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
