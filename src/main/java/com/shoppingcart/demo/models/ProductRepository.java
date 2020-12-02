package com.shoppingcart.demo.models;

import com.shoppingcart.demo.models.data.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer>{

    Product findBySlug(String slug);

    Product findBySlugAndIdNot(String slug, int id);

    List<Product> findAllByCategoryId(String categoryId, Pageable pageable);

    Page<Product> findAll(Pageable pageable);

    Long countByCategoryId(String categoryId);
}
