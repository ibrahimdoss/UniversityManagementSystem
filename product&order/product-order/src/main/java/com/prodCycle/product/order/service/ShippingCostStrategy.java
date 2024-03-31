package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.OrderEntity;

import java.math.BigDecimal;

public interface ShippingCostStrategy {

    BigDecimal calculateShippingCost(OrderEntity order);

}
