package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.domain.dto.OrderDto;
import com.prodCycle.product.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/getOrder")
    public ResponseEntity<OrderDto> getOrder(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }
}
