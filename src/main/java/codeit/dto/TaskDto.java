package codeit.dto;

import codeit.models.entities.Employee;
import codeit.models.entities.Order;
import codeit.models.entities.Project;
import codeit.models.entities.Task;
import codeit.models.enums.ProjectStatus;
import codeit.models.enums.TaskStatus;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class TaskDto {

    private String id;
    private String projectId;
    private String developerId;
    private String testerId;
    private String name;
    private String description;
    private String branchLink;
    private String startDate;
    private String dueDate;
    private String endDate;
    private String status;
    private String comment;

    public Task toTask() {

        if (id == null)
            id = UUID.randomUUID().toString().replaceAll("-", "").substring(0, 10);

        if(startDate == null)
            startDate = LocalDateTime.now().toString();

        if(status == null)
            status = "Created";

        if(developerId!=null && developerId.isEmpty())
            developerId = null;

        if(testerId!=null && testerId.isEmpty())
            testerId = null;

        return new Task.Builder()
                .setId(id)
                .setProject(new Project.Builder().setId(projectId).build())
                .setDeveloper(new Employee.Builder().setId(developerId).build())
                .setTester(new Employee.Builder().setId(testerId).build())
                .setName(name)
                .setDescription(description)
                .setBranchLink(branchLink)
                .setStartDate(LocalDateTime.parse(startDate))
                .setDueDate(LocalDateTime.of(LocalDate.parse(dueDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                        LocalTime.MIDNIGHT))
                .setEndDate(endDate == null ? null : LocalDateTime.parse(endDate))
                .setStatus(TaskStatus.getStatus(status))
                .setComment(comment)
                .build();
    }

    public static class Builder {

        TaskDto task = new TaskDto();

        public Builder setId(String id) {
            task.id = id;
            return this;
        }

        public Builder setProject(String project) {
            task.projectId = project;
            return this;
        }

        public Builder setDeveloper(String developer) {
            task.developerId = developer;
            return this;
        }

        public Builder setTester(String tester) {
            task.testerId = tester;
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

        public Builder setStartDate(String date) {
            task.startDate = date;
            return this;
        }

        public Builder setDueDate(String date) {
            task.dueDate = date;
            return this;
        }

        public Builder setEndDate(String date) {
            task.endDate = date;
            return this;
        }

        public Builder setStatus(String status) {
            task.status = status;
            return this;
        }

        public Builder setComment(String comment) {
            task.comment = comment;
            return this;
        }

        public TaskDto build() {
            return task;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getTesterId() {
        return testerId;
    }

    public void setTesterId(String testerId) {
        this.testerId = testerId;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
