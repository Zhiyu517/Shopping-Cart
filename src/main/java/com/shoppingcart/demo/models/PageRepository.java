package com.shoppingcart.demo.models;

import com.shoppingcart.demo.models.data.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends JpaRepository<Page, Integer> {

    Page findBySlug(String slug);

}
