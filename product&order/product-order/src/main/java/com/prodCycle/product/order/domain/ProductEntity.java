package com.prodCycle.product.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "products")
public class ProductEntity{

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

    @Column(name = "numberOfProduct")
    private int numberOfProduct;

    @Column(name = "weight")
    private BigDecimal weight;


    @OneToMany(mappedBy = "product")
    private Set<OrderProductEntity> orderProductEntitySet = new HashSet<>();

    private static final long serialVersionUID = 1L;

}
