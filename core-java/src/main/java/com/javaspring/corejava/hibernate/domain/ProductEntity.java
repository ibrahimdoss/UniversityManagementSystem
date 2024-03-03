package com.javaspring.corejava.hibernate.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "new_products")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "photo_url")
    private String photoUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;
}
