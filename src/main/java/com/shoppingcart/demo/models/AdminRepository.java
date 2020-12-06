package com.shoppingcart.demo.models;

import com.shoppingcart.demo.models.data.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer>{
    Admin findByUsername(String username);
}
