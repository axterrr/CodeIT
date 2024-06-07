package codeit.validators.fields;

import java.util.List;

public class FirstLastNameValidator extends AbstractFieldValidatorHandler{

    private static final String NAME_REGEX = "^[A-Z][A-Za-z]*([\\s-][A-Za-z]*)*$";

    FirstLastNameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final FirstLastNameValidator INSTANCE = new FirstLastNameValidator(FieldValidatorKey.FIRST_LAST_NAME);

    public static FirstLastNameValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 30 || !fieldValue.matches(NAME_REGEX)) {
            errors.add("Invalid name value");
        }
    }
}
