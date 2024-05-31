package codeit.validators.entities;

import codeit.dto.EmployeeDto;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoValidator {
    private static final EmployeeDtoValidator INSTANCE = new EmployeeDtoValidator();

    public static EmployeeDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
