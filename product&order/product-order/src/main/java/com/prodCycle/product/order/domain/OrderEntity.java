package com.prodCycle.product.order.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column
    private String orderNumber;

    @Column(name = "orderDate")
    private Date orderDate;

    @Column(name = "orderDescription")
    private String orderDescription;

    @Column(name = "price")
    private BigDecimal totalAmount;

    @Column(name = "shippingCost")
    private BigDecimal shippingCost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private ProductEntity product;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<OrderProductEntity> orderProductEntityList;

    @ManyToOne
    private UserEntity userEntity;

    public OrderEntity(ProductEntity product) {
        this.product = product;
    }

    public OrderEntity() {
    }
}
