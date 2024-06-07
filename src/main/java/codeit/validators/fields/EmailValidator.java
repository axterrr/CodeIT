package codeit.validators.fields;

import java.util.List;

public class EmailValidator extends AbstractFieldValidatorHandler{

    private static final String EMAIL_REGEX = "^[\\w-.]+@([A-Za-z\\d-]+\\.)+[A-Za-z]{2,}$";

    EmailValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final EmailValidator INSTANCE = new EmailValidator(FieldValidatorKey.EMAIL);

    public static EmailValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 30 || !fieldValue.matches(EMAIL_REGEX)) {
            errors.add("Invalid email value");
        }
    }

}


