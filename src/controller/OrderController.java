package controller;

import dao.OrderDAO;
import exceptions.DatabaseException;

public class OrderController {

    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public String addOrder() throws DatabaseException {
        return orderDAO.addOrder();
    }

    public void updateOrder(String id, double totalWeight) throws DatabaseException {
        orderDAO.updateOrder(id, totalWeight);
    }

    public void deleteOrderById(String ordTid) throws DatabaseException {
        orderDAO.deleteOrderById(ordTid);
    }
}
