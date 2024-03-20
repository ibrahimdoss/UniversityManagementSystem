package com.prodCycle.product.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponseByCategoryDto {
    private String name;
    private String category;
    private String description;
    private Double price;
}
