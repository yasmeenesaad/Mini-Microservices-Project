package org.example.orderservice.Repository;

import org.example.orderservice.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Order, Long> {
}
