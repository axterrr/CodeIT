package codeit.validators.fields;

import java.util.List;

public class NameValidator extends AbstractFieldValidatorHandler{

    private static final String NAME_REGEX = "^[A-Za-z0-9,.;+%@$():'\\-\\s]*$";
    NameValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final NameValidator INSTANCE = new NameValidator(FieldValidatorKey.NAME);

    public static NameValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 100 || !fieldValue.matches(NAME_REGEX)) {
            errors.add("Invalid name value");
        }
    }
}

