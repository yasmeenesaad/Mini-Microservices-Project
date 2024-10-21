package org.example.orderservice.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ProductService")  // No URL, just the service name
public interface ProductClient {

    @GetMapping("/products/{id}")
    Object getProductById(@PathVariable("id") Long id);
}
