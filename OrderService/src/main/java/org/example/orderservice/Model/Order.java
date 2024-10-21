package org.example.orderservice.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "\"order\"")
@Data
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
}
