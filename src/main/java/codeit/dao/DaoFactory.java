package codeit.dao;

import codeit.exceptions.ServerException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;

public class DaoFactory {

    public static final String DB_FILE = "/db.properties";
    private static final String DB_FACTORY_CLASS = "factory.class";

    private static DaoFactory daoFactory;
    private DataSource dataSource;

    public DaoFactory() {
        try {
            InitialContext ic = new InitialContext();
            dataSource = (DataSource) ic.lookup("java:comp/env/jdbc/codeit");
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }

    public ClientDao createClientDao() {
        try {
            return new ClientDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public EmployeeDao createEmployeeDao() {
        try {
            return new EmployeeDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public OrderDao createOrderDao() {
        try {
            return new OrderDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public ProjectDao createProjectDao() {
        try {
            return new ProjectDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public TaskDao createTaskDao() {
        try {
            return new TaskDao(dataSource.getConnection());
        } catch (SQLException e) {
            throw new ServerException(e);
        }
    }

    public static DaoFactory getDaoFactory() {
        if (daoFactory == null) {
            try {
                InputStream inputStream = DaoFactory.class.getResourceAsStream(DB_FILE);
                Properties dbProps = new Properties();
                dbProps.load(inputStream);
                String factoryClass = dbProps.getProperty(DB_FACTORY_CLASS);
                daoFactory = (DaoFactory) Class.forName(factoryClass).newInstance();
            } catch (Exception e) {
                throw new ServerException(e);
            }
        }
        return daoFactory;
    }
}
