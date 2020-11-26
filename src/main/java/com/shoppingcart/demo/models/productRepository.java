package com.shoppingcart.demo.models;

import com.shoppingcart.demo.models.data.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface productRepository extends JpaRepository<Product, Integer>{
}
