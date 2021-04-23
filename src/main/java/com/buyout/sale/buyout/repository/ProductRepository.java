package com.buyout.sale.buyout.repository;

import com.buyout.sale.buyout.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByProductName(String productName);
}
