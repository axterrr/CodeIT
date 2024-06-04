package codeit.models.enums;

public enum TaskStatus {

    CREATED ("Created"),
    DEVELOPING ("Developing"),
    TESTING ("Testing"),
    AWAITING_CONFIRMATION ("Awaiting for Confirmation"),
    FINISHED ("Finished"),
    CANCELLED ("Cancelled");

    private String value;

    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TaskStatus getStatus(String value) {
        for (TaskStatus status : TaskStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new RuntimeException("Status for task with such string value doesn't exist");
    }
}
