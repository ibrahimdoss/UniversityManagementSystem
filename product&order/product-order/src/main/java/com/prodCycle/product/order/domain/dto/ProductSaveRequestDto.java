package com.prodCycle.product.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductSaveRequestDto {

    private String name;

    private String category;

    private String photoUrl;

    private String description;

    private Double price;
}
