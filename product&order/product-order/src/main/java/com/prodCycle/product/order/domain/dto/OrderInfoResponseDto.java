package com.prodCycle.product.order.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoResponseDto {

    private String orderNumber;
    private Double totalAmount;
}
