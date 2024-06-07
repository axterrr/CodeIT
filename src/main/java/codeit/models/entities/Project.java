package codeit.models.entities;

import codeit.models.enums.ProjectStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Project {
    private String id;
    private Order order;
    private Employee manager;
    private String name;
    private String description;
    private String gitHubLink;
    private BigDecimal budget;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private LocalDateTime endDate;
    private ProjectStatus status;

    public static class Builder {

        Project project = new Project();

        public Builder setId(String id) {
            project.id = id;
            return this;
        }

        public Builder setOrder(Order order) {
            project.order = order;
            return this;
        }

        public Builder setManager(Employee maneger) {
            project.manager = maneger;
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

        public Builder setBudget(BigDecimal budget) {
            project.budget = budget;
            return this;
        }

        public Builder setStartDate(LocalDateTime date) {
            project.startDate = date;
            return this;
        }

        public Builder setDueDate(LocalDateTime date) {
            project.dueDate = date;
            return this;
        }

        public Builder setEndDate(LocalDateTime date) {
            project.endDate = date;
            return this;
        }

        public Builder setStatus(ProjectStatus status) {
            project.status = status;
            return this;
        }

        public Project build() {
            return project;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Employee getManager() {
        return manager;
    }

    public String getManagerId() {
        return manager == null ? null : manager.getId();
    }

    public void setManager(Employee manager) {
        this.manager = manager;
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

    public void setGitHubLink(String projectLink) {
        this.gitHubLink = projectLink;
    }

    public BigDecimal getBudget() {
        return budget;
    }

    public void setBudget(BigDecimal budget) {
        this.budget = budget;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public String getStartDateString() {
        return startDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public String getDueDateString() {
        return dueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getEndDateString() {
        return endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public ProjectStatus getStatus() {
        return status;
    }

    public void setStatus(ProjectStatus status) {
        this.status = status;
    }
}
