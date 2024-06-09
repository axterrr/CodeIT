package codeit.validators.fields;

import java.util.List;

public class DescriptionValidator extends AbstractFieldValidatorHandler{

    private static final String DESCRIPTION_REGEX = "^[A-Za-z0-9,.;+%@$():'\\-\\s]*$";
    DescriptionValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final DescriptionValidator INSTANCE = new DescriptionValidator(FieldValidatorKey.DESCRIPTION);

    public static DescriptionValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(DESCRIPTION_REGEX)) {
            errors.add("Invalid description value");
        }
    }
}
