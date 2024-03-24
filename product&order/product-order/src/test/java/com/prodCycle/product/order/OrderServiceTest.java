package com.prodCycle.product.order;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;
import com.prodCycle.product.order.mapper.OrderMapper;
import com.prodCycle.product.order.service.OrderService;
import com.prodCycle.product.order.service.ProductOrderService;
import com.prodCycle.product.order.service.SmsService;
import com.prodCycle.product.order.service.UserService;
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
public class OrderServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private ProductOrderService productOrderService;

    @Mock
    private SmsService smsService;

    @Mock
    private OrderMapper orderMapper;

    @InjectMocks
    private OrderService orderService;

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

        when(userService.findUserById(userId)).thenReturn(Optional.of(activeUser));
        when(orderMapper.orderEntityToOrderDto(any(OrderEntity.class))).thenReturn(requestDto);

        // When
        OrderRequestDto resultDto = orderService.createOrder(requestDto);

        // Then
        verify(productOrderService, times(1)).saveOrderProduct(anyList(), any(OrderEntity.class));
        verify(smsService, times(1)).sendSmsToUser(any(OrderEntity.class), eq(activeUser));
        assertEquals(requestDto, resultDto);
    }
}
