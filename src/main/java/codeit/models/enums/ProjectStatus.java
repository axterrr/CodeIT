package codeit.models.enums;

public enum ProjectStatus {

    CREATED ("Created"),
    DEVELOPING ("Developing"),
    AWAITING_CONFIRMATION ("Awaiting for Confirmation"),
    FINISHED ("Finished"),
    CANCELLED ("Cancelled");

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
