package com.shoppingcart.demo.models;

import com.shoppingcart.demo.models.data.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByName(String name);
    Category findBySlug(String slug);

    List<Category> findAllByOrderBySortingAsc();

}
