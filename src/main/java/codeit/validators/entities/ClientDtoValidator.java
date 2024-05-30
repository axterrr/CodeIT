package codeit.validators.entities;

import codeit.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

public class ClientDtoValidator {
    private static final ClientDtoValidator INSTANCE = new ClientDtoValidator();

    public static ClientDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(ClientDto clientDto) {
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
