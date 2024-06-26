package codeit.dao;

import codeit.exceptions.ServerException;
import codeit.models.entities.Order;
import codeit.models.enums.OrderStatus;
import codeit.services.ClientService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDao implements AutoCloseable {

    private static String GET_ALL = "SELECT * FROM `order`";
    private static String GET_BY_ID = "SELECT * FROM `order` WHERE order_id=?";
    private static String CREATE =
            "INSERT INTO `order` " +
            "(order_id, client_id, name, description, creation_date, due_date, cost, status) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE =
            "UPDATE `order` " +
            "SET name=?, description=?, due_date=?, cost=? " +
            "WHERE order_id=?";
    private static String DELETE = "DELETE FROM `order` WHERE order_id=?";
    private static String GET_ALL_ACCEPTED = "SELECT * FROM `order` WHERE status='Accepted'";
    private static String GET_ALL_BY_CLIENT = "SELECT * FROM `order` WHERE client_id=?";
    private static String CHANGE_STATUS = "UPDATE `order` SET status=? WHERE order_id=?";

    private static String ID = "order_id";
    private static String CLIENT_ID = "client_id";
    private static String NAME = "name";
    private static String DESCRIPTION = "description";
    private static String CREATION_DATE = "creation_date";
    private static String DUE_DATE = "due_date";
    private static String COST = "cost";
    private static String STATUS = "status";

    private Connection connection;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                orders.add(extractOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return orders;
    }

    public Order getById(String id) {
        Order order = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                order = extractOrderFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return order;
    }

    public void create(Order order) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, order.getId());
            query.setString(2, order.getClient().getId());
            query.setString(3, order.getName());
            query.setString(4, order.getDescription());
            query.setTimestamp(5, Timestamp.valueOf(order.getCreationDate()));
            query.setTimestamp(6, Timestamp.valueOf(order.getDueDate()));
            query.setBigDecimal(7, order.getCost());
            query.setString(8, order.getStatus().getValue());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void update(Order order) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, order.getName());
            query.setString(2, order.getDescription());
            query.setTimestamp(3, Timestamp.valueOf(order.getDueDate()));
            query.setBigDecimal(4, order.getCost());
            query.setString(5, order.getId());
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

    public List<Order> getAllAccepted() {
        List<Order> orders = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL_ACCEPTED)) {
            while (resultSet.next()) {
                orders.add(extractOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return orders;
    }

    public List<Order> getAllByClient(String clientId) {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement query = connection.prepareStatement(GET_ALL_BY_CLIENT)) {
            query.setString(1, clientId);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                orders.add(extractOrderFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return orders;
    }

    public void changeStatus(String status, String id) {
        try (PreparedStatement query = connection.prepareStatement(CHANGE_STATUS)) {
            query.setString(1, status);
            query.setString(2, id);
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    private static Order extractOrderFromResultSet(ResultSet resultSet) throws SQLException {
        return new Order.Builder()
                .setId(resultSet.getString(ID))
                .setClient(ClientService.getInstance().getClientById(resultSet.getString(CLIENT_ID)))
                .setName(resultSet.getString(NAME))
                .setDescription(resultSet.getString(DESCRIPTION))
                .setCreationDate(resultSet.getTimestamp(CREATION_DATE).toLocalDateTime())
                .setDueDate(resultSet.getTimestamp(DUE_DATE).toLocalDateTime())
                .setCost(resultSet.getBigDecimal(COST))
                .setStatus(OrderStatus.getStatus(resultSet.getString(STATUS)))
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
