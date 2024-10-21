package org.example.orderservice.Controller;

import org.example.orderservice.Model.Order;
import org.example.orderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        // Example: Get user info from User Service
        Object user = restTemplate.getForObject("http://localhost:8080/users/" + order.getUserId(), Object.class);
        if (user == null) {
            throw new RuntimeException("User not found");
        }
        Object product = restTemplate.getForObject("http://localhost:8082/products/" + order.getProductId(), Object.class);
        if (product == null) {
            throw new RuntimeException("Product not found");
        }
        return orderService.saveOrder(order);
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id).orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
}

