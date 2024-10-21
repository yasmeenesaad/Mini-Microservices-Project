package org.example.orderservice.Controller;

import org.example.orderservice.Model.Order;
import org.example.orderservice.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

//    @Autowired
//    private RestTemplate restTemplate;


    private final DiscoveryClient discoveryClient;
    private final RestClient restClient;

    public OrderController(DiscoveryClient discoveryClient, RestClient.Builder restClientBuilder) {
        this.discoveryClient = discoveryClient;
        restClient = restClientBuilder.build();
    }


    @PostMapping
    public Order createOrder(@RequestBody Order order) {


        ServiceInstance userService = discoveryClient.getInstances("UserService").get(0);
        Object userResponse = restClient.get()
                .uri(userService.getUri() + "/users/" + order.getUserId())
                .retrieve()
                .body(Object.class);

        ServiceInstance productService = discoveryClient.getInstances("ProductService").get(0);
        Object productResponse = restClient.get()
                .uri(productService.getUri() + "/products/" + order.getProductId())
                .retrieve()
                .body(Object.class);

        if (userResponse == null) {
            throw new RuntimeException("User not found");
        }
        if (productResponse == null) {
            throw new RuntimeException("Product not found");
        }


//        // Example: Get user info from User Service
//        Object user = restTemplate.getForObject("http://localhost:8080/users/" + order.getUserId(), Object.class);
//        if (user == null) {
//            throw new RuntimeException("User not found");
//        }
//        Object product = restTemplate.getForObject("http://localhost:8082/products/" + order.getProductId(), Object.class);
//        if (product == null) {
//            throw new RuntimeException("Product not found");
//        }
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

