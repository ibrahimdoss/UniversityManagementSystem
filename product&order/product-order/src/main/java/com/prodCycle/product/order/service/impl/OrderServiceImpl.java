package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;
import com.prodCycle.product.order.mapper.OrderMapper;
import com.prodCycle.product.order.repository.OrderRepository;
import com.prodCycle.product.order.service.OrderService;
import com.prodCycle.product.order.service.ShippingCostStrategy;
import com.prodCycle.product.order.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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

    private static final BigDecimal BASE_SHIPPING_COST = new BigDecimal("5.00");



    @Override
    @Transactional
    public void processOrder(OrderRequestDto orderRequestDto){
        UserEntity user = getUserFromRequest(orderRequestDto.getUserId());
        OrderEntity order = createOrder(orderRequestDto, user);

        processOrderItems(orderRequestDto.getProductIdList(), order);

        ShippingCostStrategy shippingCostStrategy;
        BigDecimal totalWeight = calculateTotalWeight(order);
        if (totalWeight.compareTo(new BigDecimal(String.valueOf(BASE_SHIPPING_COST))) > 0) {
            shippingCostStrategy = new WeightBasedShippingCostStrategy();
        } else {
            shippingCostStrategy = new FixedShippingCostStrategy();
        }

        BigDecimal shippingCost = shippingCostStrategy.calculateShippingCost(order);
        order.setShippingCost(shippingCost);

        orderRepository.save(order);

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

    public BigDecimal calculateTotalWeight(OrderEntity order) {
        BigDecimal totalWeight = BigDecimal.ZERO;
            ProductEntity product = order.getProduct();
            totalWeight = totalWeight.add(product.getWeight());
        return totalWeight;
    }


}
