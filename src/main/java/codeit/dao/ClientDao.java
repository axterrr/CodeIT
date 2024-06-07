package codeit.dao;

import codeit.exceptions.ServerException;
import codeit.models.entities.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements AutoCloseable {

    private static String GET_ALL = "SELECT * FROM `client`";
    private static String GET_BY_ID = "SELECT * FROM `client` WHERE client_id=?";
    private static String CREATE =
            "INSERT INTO `client` " +
            "(client_id, name, contact_person, email, phone_number, address, registration_date, description, password) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static String UPDATE =
            "UPDATE `client` " +
            "SET name=?, contact_person=?, email=?, phone_number=?, address=?, description=?, password=? " +
            "WHERE client_id=?";
    private static String DELETE = "DELETE FROM `client` WHERE client_id=?";
    private static String GET_BY_CREDENTIALS = "SELECT * FROM `client` WHERE email=? AND password=?";
    private static String GET_BY_EMAIL = "SELECT * FROM `client` WHERE email=?";

    private static String ID = "client_id";
    private static String NAME = "name";
    private static String CONTACT_PERSON = "contact_person";
    private static String EMAIL = "email";
    private static String PHONE = "phone_number";
    private static String ADDRESS = "address";
    private static String REGISTRATION_DATE = "registration_date";
    private static String DESCRIPTION = "description";
    private static String PASSWORD = "password";

    private Connection connection;

    public ClientDao(Connection connection) {
        this.connection = connection;
    }

    public List<Client> getAll() {
        List<Client> clients = new ArrayList<>();
        try (Statement query = connection.createStatement();
             ResultSet resultSet = query.executeQuery(GET_ALL)) {
            while (resultSet.next()) {
                clients.add(extractClientFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return clients;
    }

    public Client getById(String id) {
        Client client = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_ID)) {
            query.setString(1, id);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                client = extractClientFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return client;
    }

    public void create(Client client) {
        try (PreparedStatement query = connection.prepareStatement(CREATE)) {
            query.setString(1, client.getId());
            query.setString(2, client.getName());
            query.setString(3, client.getPerson());
            query.setString(4, client.getEmail());
            query.setString(5, client.getPhone());
            query.setString(6, client.getAddress());
            query.setTimestamp(7, Timestamp.valueOf(client.getRegistrationDate()));
            query.setString(8, client.getDescription());
            query.setString(9, client.getPassword());
            query.executeUpdate();
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public void update(Client client) {
        try (PreparedStatement query = connection.prepareStatement(UPDATE)) {
            query.setString(1, client.getName());
            query.setString(2, client.getPerson());
            query.setString(3, client.getEmail());
            query.setString(4, client.getPhone());
            query.setString(5, client.getAddress());
            query.setString(6, client.getDescription());
            query.setString(7, client.getPassword());
            query.setString(8, client.getId());
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

    public Client getByCredentials(String email, String password) {
        Client client = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_CREDENTIALS)) {
            query.setString(1, email);
            query.setString(2, password);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                client = extractClientFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return client;
    }

    public Client getByEmail(String email) {
        Client client = null;
        try (PreparedStatement query = connection.prepareStatement(GET_BY_EMAIL)) {
            query.setString(1, email);
            ResultSet resultSet = query.executeQuery();
            while (resultSet.next()) {
                client = extractClientFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            throw new ServerException(e);
        }
        return client;
    }

    private static Client extractClientFromResultSet(ResultSet resultSet) throws SQLException {
        return new Client.Builder()
                .setId(resultSet.getString(ID))
                .setName(resultSet.getString(NAME))
                .setPerson(resultSet.getString(CONTACT_PERSON))
                .setEmail(resultSet.getString(EMAIL))
                .setPhone(resultSet.getString(PHONE))
                .setAddress(resultSet.getString(ADDRESS))
                .setRegistrationDate(resultSet.getTimestamp(REGISTRATION_DATE).toLocalDateTime())
                .setDescription(resultSet.getString(DESCRIPTION))
                .setPassword(resultSet.getString(PASSWORD))
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
