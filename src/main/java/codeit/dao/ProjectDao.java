package codeit.dao;

import codeit.exceptions.ServerException;
import codeit.models.entities.Employee;
import codeit.models.entities.Order;
import codeit.models.entities.Project;
import codeit.models.enums.ProjectStatus;
import codeit.services.EmployeeService;
import codeit.services.OrderService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDao implements AutoCloseable {

    private static String GET_ALL = "SELECT * FROM `project`";
    private static String GET_BY_ID = "SELECT * FROM `project` WHERE project_id=?";
    private static String CREATE =
            "INSERT INTO `project` " +
            "(project_id, order_id, manager_id, name, description, project_link, budget, start_date, due_date, end_date, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE =
            "UPDATE `project` " +
            "SET manager_id=?, name=?, description=?, project_link=?, due_date=?, end_date=?, status=? " +
            "WHERE project_id=?";
    private static String DELETE = "DELETE FROM `project` WHERE project_id=?";
    private static String GET_BY_ORDER = "SELECT * FROM `project` WHERE order_id=?";

    private static String ID = "project_id";
    private static String ORDER_ID = "order_id";
    private static String MANAGER_ID = "manager_id";
    private static String NAME = "name";
    private static String DESCRIPTION = "description";
    private static String PROJECT_LINK = "project_link";
    private static String BUDGET = "budget";
    private static String START_DATE = "start_date";
    private static String DUE_DATE = "due_date";
    private static String END_DATE = "end_date";
    private static String STATUS = "status";

    private Connection connection;

    public ProjectDao(Connection connection) {
        this.connection = connection;
    }

    public List<Project> getAll() {
        List<Project> projects = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                projects.add(extractProjectFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return projects;
    }

    public Project getById(String id) {
        Project project = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                project = extractProjectFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return project;
    }

    public void create(Project project) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, project.getId());
            query.setString(2, project.getOrder().getId());
            query.setString(3, project.getManager().getId());
            query.setString(4, project.getName());
            query.setString(5, project.getDescription());
            query.setString(6, project.getGitHubLink());
            query.setBigDecimal(7, project.getBudget());
            query.setTimestamp(8, Timestamp.valueOf(project.getStartDate()));
            query.setTimestamp(9, Timestamp.valueOf(project.getDueDate()));
            query.setTimestamp(10, project.getEndDate() == null ? null :
                    Timestamp.valueOf(project.getEndDate()));
            query.setString(11, project.getStatus().getValue());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void update(Project project) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, project.getManager().getId());
            query.setString(2, project.getName());
            query.setString(3, project.getDescription());
            query.setString(4, project.getGitHubLink());
            query.setTimestamp(5, Timestamp.valueOf(project.getDueDate()));
            query.setTimestamp(6, project.getEndDate() == null ? null :
                    Timestamp.valueOf(project.getEndDate()));
            query.setString(7, project.getStatus().getValue());
            query.setString(8, project.getId());
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

    public Project getByOrder(String id) {
        Project project = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ORDER)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                project = extractProjectFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return project;
    }

    private static Project extractProjectFromResultSet(ResultSet resultSet) throws SQLException {
        return new Project.Builder()
                .setId(resultSet.getString(ID))
                .setOrder(OrderService.getInstance().getOrderById(resultSet.getString(ORDER_ID)))
                .setManager(EmployeeService.getInstance().getEmployeeById(resultSet.getString(MANAGER_ID)))
                .setName(resultSet.getString(NAME))
                .setDescription(resultSet.getString(DESCRIPTION))
                .setGitHubLink(resultSet.getString(PROJECT_LINK))
                .setBudget(resultSet.getBigDecimal(BUDGET))
                .setStartDate(resultSet.getTimestamp(START_DATE).toLocalDateTime())
                .setDueDate(resultSet.getTimestamp(DUE_DATE).toLocalDateTime())
                .setEndDate(resultSet.getTimestamp(END_DATE) == null ? null : resultSet.getTimestamp(END_DATE).toLocalDateTime())
                .setStatus(ProjectStatus.getStatus(resultSet.getString(STATUS)))
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
