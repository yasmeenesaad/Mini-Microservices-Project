package org.example.orderservice.Service;


import org.example.orderservice.Clients.ProductClient;
import org.example.orderservice.Clients.UserClient;
import org.example.orderservice.Model.Order;
import org.example.orderservice.Repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private OrderRepo orderRepo;
    private UserClient userClient;
    private ProductClient productClient;

    @Autowired
    public OrderService(OrderRepo orderRepo,UserClient userClient,ProductClient productClient) {
        this.orderRepo = orderRepo;
        this.userClient = userClient;
        this.productClient = productClient;
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

    public Order createOrder(Order order) {
        // Check if the user exists
        Object user = userClient.getUserById(order.getUserId());
        if (user == null) {
            throw new RuntimeException("Invalid User ID");
        }

        // Check if the product exists and has enough stock
        Object product = productClient.getProductById(order.getProductId());
        if (product == null ) {
            throw new RuntimeException("Product not available or insufficient quantity");
        }

        // If all checks pass, save the order
        return orderRepo.save(order);
    }
}
