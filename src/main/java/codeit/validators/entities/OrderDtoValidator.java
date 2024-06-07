package codeit.validators.entities;

import codeit.dto.OrderDto;
import codeit.validators.fields.AbstractFieldValidatorHandler;
import codeit.validators.fields.FieldValidatorKey;
import codeit.validators.fields.FieldValidatorsChainGenerator;

import java.util.ArrayList;
import java.util.List;

public class OrderDtoValidator {

    private AbstractFieldValidatorHandler fieldValidator = FieldValidatorsChainGenerator.getFieldValidatorsChain();

    private static final OrderDtoValidator INSTANCE = new OrderDtoValidator();

    public static OrderDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(OrderDto orderDto) {
        List<String> errors = new ArrayList<>();
        if(orderDto.getId() != null)
            fieldValidator.validateField(FieldValidatorKey.ID, orderDto.getId(), errors);
        fieldValidator.validateField(FieldValidatorKey.NAME, orderDto.getName(), errors);
        fieldValidator.validateField(FieldValidatorKey.DESCRIPTION, orderDto.getDescription(), errors);
        fieldValidator.validateField(FieldValidatorKey.DUE_DATE, orderDto.getDueDate(), errors);
        fieldValidator.validateField(FieldValidatorKey.CURRENCY, orderDto.getCost(), errors);
        if(orderDto.getStatus() != null)
            fieldValidator.validateField(FieldValidatorKey.ORDER_STATUS, orderDto.getStatus(), errors);
        return errors;
    }
}
