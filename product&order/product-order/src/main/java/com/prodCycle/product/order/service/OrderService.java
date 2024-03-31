package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;

import java.util.Optional;

public interface OrderService {

    Optional<OrderEntity> findById(Long id);

    void deleteOrder(Long id);

    void processOrder(OrderRequestDto orderRequestDto);

    OrderRequestDto getOrder(Long id);




}
