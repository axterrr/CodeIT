package codeit.models.entities;

import java.time.LocalDateTime;

public class Client {
    private String id;
    private String name;
    private String person;
    private String email;
    private String phone;
    private String address;
    private LocalDateTime registrationDate;
    private String description;
    private String password;

    public static class Builder {

        Client client = new Client();

        public Builder setId(String id) {
            client.id = id;
            return this;
        }

        public Builder setName(String name) {
            client.name = name;
            return this;
        }

        public Builder setPerson(String person) {
            client.person = person;
            return this;
        }

        public Builder setEmail(String email) {
            client.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            client.phone = phone;
            return this;
        }

        public Builder setAddress(String address) {
            client.address = address;
            return this;
        }

        public Builder setRegistrationDate(LocalDateTime registrationDate) {
            client.registrationDate = registrationDate;
            return this;
        }

        public Builder setDescription(String description) {
            client.description = description;
            return this;
        }

        public Builder setPassword(String password) {
            client.password = password;
            return this;
        }

        public Client build() {
            return client;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
