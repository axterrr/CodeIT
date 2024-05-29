package codeit.validators.entities;

import codeit.dto.CredentialsDto;

import java.util.ArrayList;
import java.util.List;

public class CredentialsDtoValidator {

    private static final CredentialsDtoValidator INSTANCE = new CredentialsDtoValidator();

    public static CredentialsDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(CredentialsDto credentialsDto) {
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
