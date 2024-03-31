package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;
import com.prodCycle.product.order.mapper.OrderMapper;
import com.prodCycle.product.order.repository.OrderRepository;
import com.prodCycle.product.order.service.OrderService;
import com.prodCycle.product.order.service.ProductService;
import com.prodCycle.product.order.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final SmsService smsService;
    private final UserService userService;
    private final OrderProductService orderProductService;

    @Override
    @Transactional
    public void processOrder(OrderRequestDto orderRequestDto){
        UserEntity user = getUserFromRequest(orderRequestDto.getUserId());
        OrderEntity order = createOrder(orderRequestDto, user);

        processOrderItems(orderRequestDto.getProductIdList(), order);
        notifyUser(order, user);
    }

    @Override
    public OrderRequestDto getOrder(Long id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    @Override
    public Optional<OrderEntity> findById(Long id){
        return orderRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }

    private UserEntity getUserFromRequest(Long userId) {
        return userService.findUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));
    }

    private OrderEntity createOrder(OrderRequestDto orderRequestDto, UserEntity user) {
        OrderEntity order = new OrderEntity();
        order.setOrderDescription(orderRequestDto.getOrderDescription());
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setUserEntity(user);
        return order;
    }

    private void processOrderItems(List<Long> productIdList, OrderEntity order) {
        orderProductService.saveOrderProduct(productIdList, order);
    }

    private void notifyUser(OrderEntity order, UserEntity user) {
        smsService.sendSmsToUser(order, user);
    }

}
