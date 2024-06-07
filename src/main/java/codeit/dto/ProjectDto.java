package codeit.dto;

import codeit.models.entities.Employee;
import codeit.models.entities.Order;
import codeit.models.entities.Project;
import codeit.models.enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class ProjectDto {

    private String id;
    private String orderId;
    private String managerId;
    private String name;
    private String description;
    private String gitHubLink;
    private String budget;
    private String startDate;
    private String dueDate;
    private String endDate;
    private String status;

    public Project toProject() {

        if (id == null)
            id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        if(startDate == null)
            startDate = LocalDateTime.now().toString();

        if(status == null)
            status = "Created";

        if(managerId.isEmpty())
            managerId = null;

        return new Project.Builder()
                .setId(id)
                .setOrder(new Order.Builder().setId(orderId).build())
                .setManager(new Employee.Builder().setId(managerId).build())
                .setName(name)
                .setDescription(description)
                .setGitHubLink(gitHubLink)
                .setBudget(new BigDecimal(budget))
                .setStartDate(LocalDateTime.parse(startDate))
                .setDueDate(LocalDateTime.of(LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.MIDNIGHT))
                .setEndDate(endDate == null ? null : LocalDateTime.parse(endDate))
                .setStatus(ProjectStatus.getStatus(status))
                .build();
    }

    public static class Builder {

        ProjectDto project = new ProjectDto();

        public Builder setId(String id) {
            project.id = id;
            return this;
        }

        public Builder setOrder(String order) {
            project.orderId = order;
            return this;
        }

        public Builder setManager(String maneger) {
            project.managerId = maneger;
            return this;
        }

        public Builder setName(String name) {
            project.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            project.description = description;
            return this;
        }

        public Builder setGitHubLink(String link) {
            project.gitHubLink = link;
            return this;
        }

        public Builder setBudget(String budget) {
            project.budget = budget;
            return this;
        }

        public Builder setStartDate(String date) {
            project.startDate = date;
            return this;
        }

        public Builder setDueDate(String date) {
            project.dueDate = date;
            return this;
        }

        public Builder setEndDate(String date) {
            project.endDate = date;
            return this;
        }

        public Builder setStatus(String status) {
            project.status = status;
            return this;
        }

        public ProjectDto build() {
            return project;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = managerId;
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

    public String getGitHubLink() {
        return gitHubLink;
    }

    public void setGitHubLink(String gitHubLink) {
        this.gitHubLink = gitHubLink;
    }

    public String getBudget() {
        return budget;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getDueDateString() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
