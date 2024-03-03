package com.javaspring.corejava.hibernate.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
public class ProductDto implements Serializable {

    private String name;
    private String category;
    private String photoUrl;
    private String description;
    private BigDecimal price;
}
