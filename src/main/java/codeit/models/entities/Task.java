package codeit.models.entities;

import codeit.models.enums.TaskStatus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    private String id;
    private Project project;
    private Employee developer;
    private Employee tester;
    private String name;
    private String description;
    private String branchLink;
    private LocalDateTime startDate;
    private LocalDateTime dueDate;
    private LocalDateTime endDate;
    private TaskStatus status;
    private String comment;

    public static class Builder {

        Task task = new Task();

        public Builder setId(String id) {
            task.id = id;
            return this;
        }

        public Builder setProject(Project project) {
            task.project = project;
            return this;
        }

        public Builder setDeveloper(Employee developer) {
            task.developer = developer;
            return this;
        }

        public Builder setTester(Employee tester) {
            task.tester = tester;
            return this;
        }

        public Builder setName(String name) {
            task.name = name;
            return this;
        }

        public Builder setDescription(String description) {
            task.description = description;
            return this;
        }

        public Builder setBranchLink(String link) {
            task.branchLink = link;
            return this;
        }

        public Builder setStartDate(LocalDateTime date) {
            task.startDate = date;
            return this;
        }

        public Builder setDueDate(LocalDateTime date) {
            task.dueDate = date;
            return this;
        }

        public Builder setEndDate(LocalDateTime date) {
            task.endDate = date;
            return this;
        }

        public Builder setStatus(TaskStatus status) {
            task.status = status;
            return this;
        }

        public Builder setComment(String comment) {
            task.comment = comment;
            return this;
        }

        public Task build() {
            return task;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getDeveloper() {
        return developer;
    }

    public String getDeveloperId() {
        return developer == null ? null : developer.getId();
    }

    public void setDeveloper(Employee developer) {
        this.developer = developer;
    }

    public Employee getTester() {
        return tester;
    }

    public String getTesterId() {
        return tester == null ? null : tester.getId();
    }

    public void setTester(Employee tester) {
        this.tester = tester;
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

    public String getBranchLink() {
        return branchLink;
    }

    public void setBranchLink(String branchLink) {
        this.branchLink = branchLink;
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

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
