package codeit.validators.fields;

import java.util.List;

public class CommentValidator extends AbstractFieldValidatorHandler{

    private static final String COMMENT_REGEX = "^[A-Za-z0-9,.;:'\\-\\s]*$";

    CommentValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final CommentValidator INSTANCE = new CommentValidator(FieldValidatorKey.COMMENT);

    public static CommentValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.length() > 200 || !fieldValue.matches(COMMENT_REGEX)) {
            errors.add("Invalid comment value");
        }
    }
}
