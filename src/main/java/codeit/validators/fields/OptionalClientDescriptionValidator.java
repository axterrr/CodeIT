package codeit.validators.fields;

import java.util.List;

public class OptionalClientDescriptionValidator extends AbstractFieldValidatorHandler{

    private static final String DESCRIPTION_REGEX = "^[A-Za-z0-9,.;+%@$():'\\-\\s]*$";

    OptionalClientDescriptionValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final OptionalClientDescriptionValidator INSTANCE = new OptionalClientDescriptionValidator(FieldValidatorKey.OPTIONAL_CLIENT_DESCRIPTION);

    public static OptionalClientDescriptionValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.length() > 200 || (!fieldValue.isEmpty() && !fieldValue.matches(DESCRIPTION_REGEX))) {
            errors.add("Invalid description value");
        }
    }
}

