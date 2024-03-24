package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;
import com.prodCycle.product.order.exception.UserNotValidException;
import com.prodCycle.product.order.mapper.OrderMapper;
import com.prodCycle.product.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final SmsService smsService;
    private final UserService userService;
    private final ProductOrderService productOrderService;

    @Transactional
    public OrderRequestDto createOrder(OrderRequestDto orderRequestDto){
        List<Long> productIdList = orderRequestDto.getProductIdList();
        final String orderDescription = orderRequestDto.getOrderDescription();
        final Long userId = orderRequestDto.getUserId();

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderDescription(orderDescription);
        orderEntity.setOrderNumber(UUID.randomUUID().toString());
        Optional<UserEntity> userById = userService.findUserById(userId);
        UserEntity userEntity = userById.get();
        if (!userEntity.getIsActive()){
            throw new UserNotValidException(userEntity.getId());
        }

        productOrderService.saveOrderProduct(productIdList, orderEntity);

        smsService.sendSmsToUser(orderEntity, userEntity);
        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    public OrderRequestDto getOrder(Long id){
        OrderEntity orderEntity = orderRepository.findById(id).orElseThrow();
        return orderMapper.orderEntityToOrderDto(orderEntity);
    }

    @Transactional
    public void deleteOrder(Long id){
        orderRepository.deleteById(id);
    }
}
