package codeit.validators.fields;

import codeit.models.enums.ProjectStatus;

import java.util.List;

public class ProjectStatusValidator extends AbstractFieldValidatorHandler{

    ProjectStatusValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final ProjectStatusValidator INSTANCE = new ProjectStatusValidator(FieldValidatorKey.PROJECT_STATUS);

    public static ProjectStatusValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!isStatus(fieldValue)) {
            errors.add("Invalid status value");
        }
    }

    private boolean isStatus(String fieldValue) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.getValue().equals(fieldValue)) {
                return true;
            }
        }
        return false;
    }
}

