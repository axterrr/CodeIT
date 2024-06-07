package codeit.validators.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EndDateValidator extends AbstractFieldValidatorHandler{

    EndDateValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final EndDateValidator INSTANCE = new EndDateValidator(FieldValidatorKey.END_DATE);

    public static EndDateValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(fieldValue);
            Date today = new Date();
            if (date.after(today)) {
                errors.add("Date can't be in the future");
            }
        } catch (ParseException e) {
            errors.add("Invalid date value");
        }
    }

}

