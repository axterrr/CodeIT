package codeit.validators.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DueDateValidator extends AbstractFieldValidatorHandler{

    DueDateValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final DueDateValidator INSTANCE = new DueDateValidator(FieldValidatorKey.DUE_DATE);

    public static DueDateValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(fieldValue);
            Date today = new Date();
            if (date.before(today)) {
                errors.add("Date must be in the future");
            }
        } catch (ParseException e) {
            errors.add("Invalid date value");
        }
    }

}

