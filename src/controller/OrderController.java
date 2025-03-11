package controller;

import dao.OrderDAO;
import exceptions.DatabaseException;

public class OrderController {

    private final OrderDAO orderDAO;

    public OrderController(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void addOrder(String totalWeight) throws DatabaseException {
        orderDAO.addOrder(totalWeight);
    }

    public void deleteOrderById(String ordTid) throws DatabaseException {
        orderDAO.deleteOrderById(ordTid);
    }
}
