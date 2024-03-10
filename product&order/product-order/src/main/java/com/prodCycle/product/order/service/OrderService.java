package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.dto.OrderDto;
import com.prodCycle.product.order.mapper.OrderMapper;
import com.prodCycle.product.order.repository.OrderRepository;
import com.prodCycle.product.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final ProductRepository productRepository;

    public OrderDto createOrder(OrderDto orderDto){
        ProductEntity productEntity = productRepository.findById(orderDto.getProductId()).orElseThrow();
        OrderEntity orderEntity = new OrderEntity(productEntity);
        orderEntity = orderRepository.save(orderEntity);
        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    public OrderDto getOrder(Long id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    @Transactional
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
