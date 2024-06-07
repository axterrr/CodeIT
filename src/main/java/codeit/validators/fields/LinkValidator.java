package codeit.validators.fields;

import java.util.List;

public class LinkValidator extends AbstractFieldValidatorHandler{

    private static final String LINK_REGEX = "^https?://[\\da-z.-]+\\.[a-z]{2,}(/[\\w .-]*)*/?$";

    LinkValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final LinkValidator INSTANCE = new LinkValidator(FieldValidatorKey.LINK);

    public static LinkValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || fieldValue.length() > 100 || !fieldValue.matches(LINK_REGEX)) {
            errors.add("Invalid link value");
        }
    }

}

