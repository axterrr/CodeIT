package codeit.models.enums;

public enum OrderStatus {

    PENDING ("pending"),
    ACCEPTED ("accepted"),
    DEVELOPING ("developing"),
    DONE ("done"),
    REJECTED ("rejected"),
    CANCELLED ("cancelled");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static OrderStatus getStatus(String value) {
        for (OrderStatus status : OrderStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new RuntimeException("Status for order with such string value doesn't exist");
    }
}
