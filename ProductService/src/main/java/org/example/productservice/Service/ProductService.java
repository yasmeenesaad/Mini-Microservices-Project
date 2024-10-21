package org.example.productservice.Service;

import org.example.productservice.Model.Product;
import org.example.productservice.Repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepo productRepo;
    @Autowired
    public ProductService(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }
    public Optional<Product> getProductById(Long id) {
        return productRepo.findById(id);
    }
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

}
