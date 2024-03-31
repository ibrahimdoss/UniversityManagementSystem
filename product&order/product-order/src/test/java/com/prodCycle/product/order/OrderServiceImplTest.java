package com.prodCycle.product.order;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;
import com.prodCycle.product.order.mapper.OrderMapper;
import com.prodCycle.product.order.service.OrderService;
import com.prodCycle.product.order.service.impl.OrderServiceImpl;
import com.prodCycle.product.order.service.impl.OrderProductService;
import com.prodCycle.product.order.service.impl.SmsService;
import com.prodCycle.product.order.service.impl.UserServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private UserServiceImpl userServiceImpl;

    @Mock
    private OrderProductService orderProductService;

    @Mock
    private SmsService smsService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before("setUp")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateOrderActiveUser() {
        // Given
        Long userId = 1L;
        UserEntity activeUser = new UserEntity();
        activeUser.setIsActive(true);
        activeUser.setId(userId);
        OrderRequestDto requestDto = new OrderRequestDto();
        requestDto.setUserId(userId);
        requestDto.setProductIdList(Arrays.asList(1L, 2L));
        requestDto.setOrderDescription("New Order");

        when(userServiceImpl.findUserById(userId)).thenReturn(Optional.of(activeUser));

        // When
        orderService.processOrder(requestDto);

        // Then
        verify(orderProductService, times(1)).saveOrderProduct(anyList(), any(OrderEntity.class));
        verify(smsService, times(1)).sendSmsToUser(any(OrderEntity.class), eq(activeUser));
    }

}
