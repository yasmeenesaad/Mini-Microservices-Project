package org.example.productservice.Repository;

import org.example.productservice.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
}
