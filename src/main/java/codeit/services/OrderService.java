package codeit.services;

import codeit.dao.OrderDao;
import codeit.dao.DaoFactory;
import codeit.models.entities.Order;

import java.util.List;

public class OrderService {

    private static final OrderService INSTANCE = new OrderService(DaoFactory.getDaoFactory());
    private final DaoFactory daoFactory;
    OrderService(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    public static OrderService getInstance() {
        return OrderService.INSTANCE;
    }

    public List<Order> getAllOrders() {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.getAll();
        }
    }

    public Order getOrderById(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.getById(orderId);
        }
    }

    public void createOrder(Order order) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.create(order);
        }
    }

    public void updateOrder(Order order) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.update(order);
        }
    }

    public void deleteOrder(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.delete(orderId);
        }
    }
}
