package codeit.models.entities;

import codeit.models.enums.OrderStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    private String id;
    private Client client;
    private String name;
    private String description;
    private LocalDateTime creationDate;
    private LocalDateTime dueDate;
    private BigDecimal cost;
    private OrderStatus status;

    public static class Builder {

        Order order = new Order();

        public Builder setId(String id) {
            order.id = id;
            return this;
        }

        public Builder setClient(Client client) {
            order.client = client;
            return this;
        }

        public Builder setName(String name) {
            order.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            order.description = description;
            return this;
        }

        public Builder setCreationDate(LocalDateTime creationDate) {
            order.creationDate = creationDate;
            return this;
        }

        public Builder setDueDate(LocalDateTime dueDate) {
            order.dueDate = dueDate;
            return this;
        }

        public Builder setCost(BigDecimal cost) {
            order.cost = cost;
            return this;
        }

        public Builder setStatus(OrderStatus status) {
            order.status = status;
            return this;
        }

        public Order build() {
            return order;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public String getCreationDateString() {
        return creationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public String getDueDateString() {
        return dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
