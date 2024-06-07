package codeit.validators.fields;

import java.util.List;

public class AddressValidator extends AbstractFieldValidatorHandler{
    private static final String ADDRESS_REGEX = "^[A-Za-z(\\s,\\-)\\d]*$";

    AddressValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final AddressValidator INSTANCE = new AddressValidator(FieldValidatorKey.ADDRESS);

    public static AddressValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if (fieldValue.isEmpty() || fieldValue.length() > 100 || !fieldValue.matches(ADDRESS_REGEX)) {
            errors.add("Invalid address value");
        }
    }
}
