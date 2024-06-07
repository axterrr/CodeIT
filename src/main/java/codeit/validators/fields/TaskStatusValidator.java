package codeit.validators.fields;

import codeit.models.enums.TaskStatus;

import java.util.List;

public class TaskStatusValidator extends AbstractFieldValidatorHandler{

    TaskStatusValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final TaskStatusValidator INSTANCE = new TaskStatusValidator(FieldValidatorKey.TASK_STATUS);

    public static TaskStatusValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!isStatus(fieldValue)) {
            errors.add("Invalid status value");
        }
    }

    private boolean isStatus(String fieldValue) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.getValue().equals(fieldValue)) {
                return true;
            }
        }
        return false;
    }
}
