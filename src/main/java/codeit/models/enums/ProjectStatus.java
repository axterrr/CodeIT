package codeit.models.enums;

public enum ProjectStatus {

    CREATED ("created"),
    DEVELOPING ("developing"),
    AWAITING_CONFIRMATION ("awaitingConfirmation"),
    FINISHED ("finished"),
    CANCELLED ("cancelled");

    private String value;

    ProjectStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static ProjectStatus getStatus(String value) {
        for (ProjectStatus status : ProjectStatus.values()) {
            if (status.getValue().equals(value)) {
                return status;
            }
        }
        throw new RuntimeException("Status for project with such string value doesn't exist");
    }
}
