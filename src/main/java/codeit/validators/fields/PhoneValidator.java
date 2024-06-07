package codeit.validators.fields;

import java.util.List;

public class PhoneValidator extends AbstractFieldValidatorHandler{

    private static final String PHONE_REGEX = "^(\\+)?\\d{7,12}$";

    PhoneValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final PhoneValidator INSTANCE = new PhoneValidator(FieldValidatorKey.PHONE);

    public static PhoneValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 13 || !fieldValue.matches(PHONE_REGEX)) {
            errors.add("Invalid phone number value");
        }
    }

}
