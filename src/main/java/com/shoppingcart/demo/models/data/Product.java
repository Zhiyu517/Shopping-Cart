package com.shoppingcart.demo.models.data;

import com.sun.tools.javah.Gen;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="products")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String slug;

    private String description;

    private String image;

    private String price;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAd;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAd;
}
