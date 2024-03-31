package com.prodCycle.product.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductSaveRequestDto {

    private String name;

    private String category;

    private String photoUrl;

    private String description;

    private BigDecimal price;

    private int numberOfProduct;

    private BigDecimal weight;
}
