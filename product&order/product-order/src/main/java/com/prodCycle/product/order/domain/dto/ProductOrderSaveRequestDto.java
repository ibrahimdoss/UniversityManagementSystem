package com.prodCycle.product.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOrderSaveRequestDto {

    private Long productId;
    private String orderDescription;
}
