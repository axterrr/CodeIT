package codeit.validators.entities;

import codeit.dto.ProjectDto;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class ProjectDtoValidator {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static final ProjectDtoValidator INSTANCE = new ProjectDtoValidator();

    public static ProjectDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(ProjectDto projectDto) {
        List<String> errors = new ArrayList<>();
        if(projectDto.getId() != null)
            fieldValidator.validateField(FieldValidatorKey.ID, projectDto.getId(), errors);
        if(projectDto.getId() == null)
            fieldValidator.validateField(FieldValidatorKey.ID, projectDto.getOrderId(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_ID, projectDto.getManagerId(), errors);
        fieldValidator.validateField(FieldValidatorKey.NAME, projectDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.DESCRIPTION, projectDto.getDescription(), errors);
        fieldValidator.validateField(FieldValidatorKey.LINK, projectDto.getGitHubLink(), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, projectDto.getBudget(), errors);
        fieldValidator.validateField(FieldValidatorKey.DUE_DATE, projectDto.getDueDateString(), errors);
        if(projectDto.getEndDate() != null)
            fieldValidator.validateField(FieldValidatorKey.END_DATE, projectDto.getEndDate(), errors);
        if(projectDto.getStatus() != null)
            fieldValidator.validateField(FieldValidatorKey.PROJECT_STATUS, projectDto.getStatus(), errors);
        return errors;
    }
}
