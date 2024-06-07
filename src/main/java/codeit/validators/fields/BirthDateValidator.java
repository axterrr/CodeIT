package codeit.validators.fields;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BirthDateValidator extends AbstractFieldValidatorHandler{

    BirthDateValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final BirthDateValidator INSTANCE = new BirthDateValidator(FieldValidatorKey.BIRTH_DATE);

    public static BirthDateValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(fieldValue.isEmpty()) {
            errors.add("Invalid date value");
            return;
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = dateFormat.parse(fieldValue);
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.YEAR, -18);
            Date eighteenYearsAgo = calendar.getTime();
            if (date.after(eighteenYearsAgo)) {
                errors.add("Employee must be more than 18 years old");
            }
            calendar.add(Calendar.YEAR, -82);
            Date hundredYearsAgo = calendar.getTime();
            if (date.before(hundredYearsAgo)) {
                errors.add("Employee must be less than 100 years old");
            }
        } catch (ParseException e) {
            errors.add("Invalid date value");
        }
    }
}
