package codeit.dto;

import codeit.controller.utils.SessionManager;
import codeit.models.entities.Client;
import codeit.models.entities.Order;
import codeit.models.enums.OrderStatus;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class OrderDto {

    private String id;
    private String clientId;
    private String name;
    private String description;
    private String creationDate;
    private String dueDate;
    private String cost;
    private String status;

    public Order toOrder(HttpServletRequest request) {

        if (id == null)
            id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        if(clientId == null)
            clientId = SessionManager.getInstance().getClientFromSession(request.getSession()).getId();

        if(creationDate == null)
            creationDate = LocalDateTime.now().toString();

        if(status == null)
            status = "Pending";

        return new Order.Builder()
                .setId(id)
                .setClient(new Client.Builder().setId(clientId).build())
                .setName(name)
                .setDescription(description)
                .setCreationDate(LocalDateTime.parse(creationDate))
                .setDueDate(LocalDateTime.of(LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.MIDNIGHT))
                .setCost(new BigDecimal(cost))
                .setStatus(OrderStatus.getStatus(status))
                .build();
    }

    public static class Builder {

        OrderDto order = new OrderDto();

        public Builder setId(String id) {
            order.id = id;
            return this;
        }

        public Builder setClient(String client) {
            order.clientId = client;
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

        public Builder setCreationDate(String creationDate) {
            order.creationDate = creationDate;
            return this;
        }

        public Builder setDueDate(String dueDate) {
            order.dueDate = dueDate;
            return this;
        }

        public Builder setCost(String cost) {
            order.cost = cost;
            return this;
        }

        public Builder setStatus(String status) {
            order.status = status;
            return this;
        }

        public OrderDto build() {
            return order;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
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

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
