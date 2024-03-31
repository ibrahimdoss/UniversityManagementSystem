package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.service.ShippingCostStrategy;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class WeightBasedShippingCostStrategy implements ShippingCostStrategy {

    private static final BigDecimal BASE_SHIPPING_COST = new BigDecimal("5.00");
    private static final BigDecimal WEIGHT_COST_FACTOR = new BigDecimal("15");


    @Override
    public BigDecimal calculateShippingCost(OrderEntity order) {
        BigDecimal totalWeight = order.getProduct().getWeight();

        BigDecimal weightCost = totalWeight.multiply(WEIGHT_COST_FACTOR);
        return BASE_SHIPPING_COST.add(weightCost);
    }
}
