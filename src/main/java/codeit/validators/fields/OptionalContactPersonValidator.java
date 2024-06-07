package codeit.validators.fields;

import java.util.List;

public class OptionalContactPersonValidator extends AbstractFieldValidatorHandler{

    private static final String CONTACT_REGEX = "^[A-Za-z0-9,.;:'\\-\\s]*$";

    OptionalContactPersonValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final OptionalContactPersonValidator INSTANCE = new OptionalContactPersonValidator(FieldValidatorKey.OPTIONAL_CONTACT_PERSON);

    public static OptionalContactPersonValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.length() > 100 || (!fieldValue.isEmpty() && !fieldValue.matches(CONTACT_REGEX))) {
            errors.add("Invalid description value");
        }
    }
}


