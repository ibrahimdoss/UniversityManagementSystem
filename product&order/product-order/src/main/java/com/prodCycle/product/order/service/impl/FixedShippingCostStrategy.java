package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.service.ShippingCostStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class FixedShippingCostStrategy implements ShippingCostStrategy {
    @Override
    public BigDecimal calculateShippingCost(OrderEntity order) {
        return new BigDecimal("10.00");
    }
}
