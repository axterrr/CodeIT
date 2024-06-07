package codeit.validators.entities;

import codeit.dto.EmployeeDto;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDtoValidator {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static final EmployeeDtoValidator INSTANCE = new EmployeeDtoValidator();

    public static EmployeeDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(EmployeeDto employeeDto) {
        List<String> errors = new ArrayList<>();
        if(employeeDto.getId() != null)
            fieldValidator.validateField(FieldValidatorKey.ID, employeeDto.getId(), errors);
        fieldValidator.validateField(FieldValidatorKey.FIRST_LAST_NAME, employeeDto.getFirstName(), errors);
        fieldValidator.validateField(FieldValidatorKey.FIRST_LAST_NAME, employeeDto.getLastName(), errors);
        fieldValidator.validateField(FieldValidatorKey.ROLE, employeeDto.getRoleString(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_SPECIALISATION, employeeDto.getSpecialisation(), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, employeeDto.getSalary(), errors);
        fieldValidator.validateField(FieldValidatorKey.EMAIL, employeeDto.getEmail(), errors);
        fieldValidator.validateField(FieldValidatorKey.PHONE, employeeDto.getPhone(), errors);
        fieldValidator.validateField(FieldValidatorKey.ADDRESS, employeeDto.getAddress(), errors);
        fieldValidator.validateField(FieldValidatorKey.BIRTH_DATE, employeeDto.getBirthDateString(), errors);
        fieldValidator.validateField(FieldValidatorKey.PASSWORD, employeeDto.getPassword(), errors);
        if (!employeeDto.getPassword().equals(employeeDto.getConfirmPassword()))
            errors.add("Incorrect confirm password");
        return errors;
    }
}
