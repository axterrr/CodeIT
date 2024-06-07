package codeit.validators.fields;

import codeit.models.enums.Role;

import java.util.List;

public class RoleValidator extends AbstractFieldValidatorHandler{

    RoleValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final RoleValidator INSTANCE = new RoleValidator(FieldValidatorKey.ROLE);

    public static RoleValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!isRole(fieldValue)) {
            errors.add("Invalid role value");
        }
    }

    private boolean isRole(String fieldValue) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(fieldValue)) {
                return true;
            }
        }
        return false;
    }
}
