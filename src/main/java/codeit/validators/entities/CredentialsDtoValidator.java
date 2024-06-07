package codeit.validators.entities;

import codeit.dto.CredentialsDto;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class CredentialsDtoValidator {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static final CredentialsDtoValidator INSTANCE = new CredentialsDtoValidator();

    public static CredentialsDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(CredentialsDto credentialsDto) {
        List<String> errors = new ArrayList<>();
        fieldValidator.validateField(FieldValidatorKey.EMAIL, credentialsDto.getEmail(), errors);
        return errors;
    }
}
