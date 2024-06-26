package codeit.services;

import codeit.dao.OrderDao;
import codeit.dao.DaoFactory;
import codeit.models.entities.Order;
import codeit.models.enums.OrderStatus;

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

    public List<Order> getAllAcceptedOrders() {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.getAllAccepted();
        }
    }

    public List<Order> getAllOrdersByClient(String clientId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            return dao.getAllByClient(clientId);
        }
    }

    public void cancelOrder(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.changeStatus(OrderStatus.CANCELLED.getValue(), orderId);
        }
    }

    public void acceptOrder(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.changeStatus(OrderStatus.ACCEPTED.getValue(), orderId);
        }
    }

    public void rejectOrder(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.changeStatus(OrderStatus.REJECTED.getValue(), orderId);
        }
    }

    public void completeOrder(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.changeStatus(OrderStatus.DONE.getValue(), orderId);
        }
    }

    public void startDevelopingOrder(String orderId) {
        try (OrderDao dao = daoFactory.createOrderDao()) {
            dao.changeStatus(OrderStatus.DEVELOPING.getValue(), orderId);
        }
    }
}
