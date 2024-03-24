package com.prodCycle.product.order.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserEntity extends BaseEntity{
    private String name;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Boolean isActive;

}
