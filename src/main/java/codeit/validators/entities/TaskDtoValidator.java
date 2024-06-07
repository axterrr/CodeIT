package codeit.validators.entities;

import codeit.dto.TaskDto;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class TaskDtoValidator {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static final TaskDtoValidator INSTANCE = new TaskDtoValidator();

    public static TaskDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(TaskDto taskDto) {
        List<String> errors = new ArrayList<>();
        if(taskDto.getId() != null)
            fieldValidator.validateField(FieldValidatorKey.ID, taskDto.getId(), errors);
        if(taskDto.getId() == null)
            fieldValidator.validateField(FieldValidatorKey.ID, taskDto.getProjectId(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_ID, taskDto.getDeveloperId(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_ID, taskDto.getTesterId(), errors);
        fieldValidator.validateField(FieldValidatorKey.NAME, taskDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.DESCRIPTION, taskDto.getDescription(), errors);
        fieldValidator.validateField(FieldValidatorKey.LINK, taskDto.getBranchLink(), errors);
        fieldValidator.validateField(FieldValidatorKey.DUE_DATE, taskDto.getDueDateString(), errors);
        if(taskDto.getEndDate() != null)
            fieldValidator.validateField(FieldValidatorKey.END_DATE, taskDto.getEndDate(), errors);
        if(taskDto.getStatus() != null)
            fieldValidator.validateField(FieldValidatorKey.TASK_STATUS, taskDto.getStatus(), errors);
        if(taskDto.getComment() != null)
            fieldValidator.validateField(FieldValidatorKey.COMMENT, taskDto.getComment(), errors);
        return errors;
    }
}
