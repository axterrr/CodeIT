package codeit.validators.fields;

import java.util.List;

public class OptionalSpecialisationValidator extends AbstractFieldValidatorHandler{

    private static final String SPECIALISATION_REGEX = "^[A-Za-z0-9,.;:'\\-\\s]*$";

    OptionalSpecialisationValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final OptionalSpecialisationValidator INSTANCE = new OptionalSpecialisationValidator(FieldValidatorKey.OPTIONAL_SPECIALISATION);

    public static OptionalSpecialisationValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.length() > 100 || (!fieldValue.isEmpty() && !fieldValue.matches(SPECIALISATION_REGEX))) {
            errors.add("Invalid specialisation value");
        }
    }
}
