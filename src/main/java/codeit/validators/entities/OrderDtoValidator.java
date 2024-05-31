package codeit.validators.entities;

import codeit.dto.OrderDto;

import java.util.ArrayList;
import java.util.List;

public class OrderDtoValidator {
    private static final OrderDtoValidator INSTANCE = new OrderDtoValidator();

    public static OrderDtoValidator getInstance() {
        return INSTANCE;
    }

    public List<String> validate(OrderDto orderDto) {
        List<String> errors = new ArrayList<>();
        return errors;
    }
}
