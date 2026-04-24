package controller;

import repository.OrderRepository;
import exceptions.DatabaseException;

public class OrderController {

    private final OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    public String addOrder() throws DatabaseException {
        return orderRepo.addOrder();
    }

    public void updateOrder(String id, double totalWeight) throws DatabaseException {
        orderRepo.updateOrder(id, totalWeight);
    }

    public void deleteOrderById(String ordTid) throws DatabaseException {
        orderRepo.deleteOrderById(ordTid);
    }
}
