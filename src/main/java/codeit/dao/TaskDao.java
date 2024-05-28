package codeit.dao;

import codeit.exceptions.ServerException;
import codeit.models.entities.Employee;
import codeit.models.entities.Project;
import codeit.models.entities.Task;
import codeit.models.enums.TaskStatus;
import codeit.services.EmployeeService;
import codeit.services.ProjectService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TaskDao implements AutoCloseable {

    private static String GET_ALL = "SELECT * FROM `task`";
    private static String GET_BY_ID = "SELECT * FROM `task` WHERE task_id=?";
    private static String CREATE =
            "INSERT INTO `task` " +
            "(task_id, project_id, developer_id, tester_id, name, description, task_link, start_date, due_date, end_date, status, comment) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE =
            "UPDATE `task` " +
            "SET developer_id=?, tester_id=?, name=?, description=?, task_link=?, due_date=?, end_date=?, status=?, comment=? " +
            "WHERE task_id=?";
    private static String DELETE = "DELETE FROM `task` WHERE task_id=?";

    private static String ID = "task_id";
    private static String PROJECT_ID = "project_id";
    private static String DEVELOPER_ID = "developer_id";
    private static String TESTER_ID = "tester_id";
    private static String NAME = "name";
    private static String DESCRIPTION = "description";
    private static String TASK_LINK = "task_link";
    private static String START_DATE = "start_date";
    private static String DUE_DATE = "due_date";
    private static String END_DATE = "end_date";
    private static String STATUS = "status";
    private static String COMMENT = "status";

    private Connection connection;

    public TaskDao(Connection connection) {
        this.connection = connection;
    }

    public List<Task> getAll() {
        List<Task> tasks = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                tasks.add(extractTaskFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return tasks;
    }

    public Task getById(String id) {
        Task task = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                task = extractTaskFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return task;
    }

    public void create(Task task) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, task.getId());
            query.setString(2, task.getProject().getId());
            query.setString(3, task.getDeveloper().getId());
            query.setString(4, task.getTester().getId());
            query.setString(5, task.getName());
            query.setString(6, task.getDescription());
            query.setString(7, task.getBranchLink());
            query.setTimestamp(8, Timestamp.valueOf(task.getStartDate()));
            query.setTimestamp(9, Timestamp.valueOf(task.getDueDate()));
            query.setTimestamp(10, Timestamp.valueOf(task.getEndDate()));
            query.setString(11, task.getStatus().getValue());
            query.setString(12, task.getComment());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void update(Task task) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, task.getDeveloper().getId());
            query.setString(2, task.getTester().getId());
            query.setString(3, task.getName());
            query.setString(4, task.getDescription());
            query.setString(5, task.getBranchLink());
            query.setTimestamp(6, Timestamp.valueOf(task.getDueDate()));
            query.setTimestamp(7, Timestamp.valueOf(task.getEndDate()));
            query.setString(8, task.getStatus().getValue());
            query.setString(9, task.getComment());
            query.setString(10, task.getId());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void delete(String id) {
        try (PreparedStatement query = connection.prepareStatement(DELETE)) {
            query.setString(1, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    private static Task extractTaskFromResultSet(ResultSet resultSet) throws SQLException {
        return new Task.Builder()
                .setId(resultSet.getString(ID))
                .setProject(ProjectService.getInstance().getProjectById(resultSet.getString(PROJECT_ID)))
                .setDeveloper(EmployeeService.getInstance().getEmployeeById(resultSet.getString(DEVELOPER_ID)))
                .setTester(EmployeeService.getInstance().getEmployeeById(resultSet.getString(TESTER_ID)))
                .setName(resultSet.getString(NAME))
                .setDescription(resultSet.getString(DESCRIPTION))
                .setBranchLink(resultSet.getString(TASK_LINK))
                .setStartDate(resultSet.getTimestamp(START_DATE).toLocalDateTime())
                .setDueDate(resultSet.getTimestamp(DUE_DATE).toLocalDateTime())
                .setEndDate(resultSet.getTimestamp(END_DATE).toLocalDateTime())
                .setStatus(TaskStatus.getStatus(resultSet.getString(STATUS)))
                .setComment(resultSet.getString(COMMENT))
                .build();
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }
}