package codeit.validators.fields;

import java.util.List;

public class IdValidator extends AbstractFieldValidatorHandler{
    private static final String ID_REGEX = "^[A-Za-z\\d]*$";

    IdValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final IdValidator INSTANCE = new IdValidator(FieldValidatorKey.ID);

    public static IdValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty() || !fieldValue.matches(ID_REGEX)) {
            errors.add("Invalid ID value");
        }
    }
}
