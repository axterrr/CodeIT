package codeit.validators.fields;

import java.util.List;

public class OptionalIdValidator extends AbstractFieldValidatorHandler{

    private static final String ID_REGEX = "^[A-Za-z\\d]*$";

    OptionalIdValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final OptionalIdValidator INSTANCE = new OptionalIdValidator(FieldValidatorKey.OPTIONAL_ID);

    public static OptionalIdValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!fieldValue.isEmpty() && !fieldValue.matches(ID_REGEX)) {
            errors.add("Invalid ID value");
        }
    }
}
