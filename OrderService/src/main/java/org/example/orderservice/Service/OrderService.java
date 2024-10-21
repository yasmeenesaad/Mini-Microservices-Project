package org.example.orderservice.Service;


import org.example.orderservice.Model.Order;
import org.example.orderservice.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepo orderRepo;

    @Autowired
    public OrderService(OrderRepo orderRepo) {
        this.orderRepo = orderRepo;
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepo.findById(id);
    }

    public Order saveOrder(Order order) {
        return orderRepo.save(order);
    }
}
