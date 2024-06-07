package codeit.validators.fields;

import codeit.models.enums.OrderStatus;

import java.util.List;

public class OrderStatusValidator extends AbstractFieldValidatorHandler{

    OrderStatusValidator(FieldValidatorKey fieldValidatorKey) {
        super(fieldValidatorKey);
    }

    private static final OrderStatusValidator INSTANCE = new OrderStatusValidator(FieldValidatorKey.ORDER_STATUS);

    public static OrderStatusValidator getInstance() {
        return INSTANCE;
    }

    @Override
    void validateField(String fieldValue, List<String> errors) {
        if(!isStatus(fieldValue)) {
            errors.add("Invalid status value");
        }
    }

    private boolean isStatus(String fieldValue) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equals(fieldValue)) {
                return true;
            }
        }
        return false;
    }
}
