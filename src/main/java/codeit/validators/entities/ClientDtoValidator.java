package codeit.validators.entities;

import codeit.dto.ClientDto;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class ClientDtoValidator {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static final ClientDtoValidator INSTANCE = new ClientDtoValidator();

    public static ClientDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();
        if(clientDto.getId() != null)
            fieldValidator.validateField(FieldValidatorKey.ID, clientDto.getId(), errors);
        fieldValidator.validateField(FieldValidatorKey.NAME, clientDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_CONTACT_PERSON, clientDto.getPerson(), errors);
        fieldValidator.validateField(FieldValidatorKey.EMAIL, clientDto.getEmail(), errors);
        fieldValidator.validateField(FieldValidatorKey.PHONE, clientDto.getPhone(), errors);
        fieldValidator.validateField(FieldValidatorKey.ADDRESS, clientDto.getAddress(), errors);
        fieldValidator.validateField(FieldValidatorKey.OPTIONAL_CLIENT_DESCRIPTION, clientDto.getDescription(), errors);
        fieldValidator.validateField(FieldValidatorKey.PASSWORD, clientDto.getPassword(), errors);
        if (!clientDto.getPassword().equals(clientDto.getConfirmPassword()))
            errors.add("Incorrect confirm password");
        return errors;
    }
}
