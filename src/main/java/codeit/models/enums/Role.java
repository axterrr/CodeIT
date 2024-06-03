package codeit.models.enums;

public enum Role {

    CEO ("CEO"),
    PROJECT_MANAGER ("Project Manager"),
    DEVELOPER ("Developer"),
    TESTER ("Tester");

    private String value;

    Role(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static Role getRole(String value) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        throw new RuntimeException("Role with such string value doesn't exist");
    }
}
