package com.shoppingcart.demo.models;

import com.shoppingcart.demo.models.data.Admin;
import com.shoppingcart.demo.models.data.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUsername(String username);
}
