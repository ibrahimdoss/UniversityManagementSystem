package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.domain.dto.OrderRequestDto;
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
    public ResponseEntity<OrderRequestDto> createOrder(@RequestBody OrderRequestDto orderRequestDto) {
        return ResponseEntity.ok(orderService.createOrder(orderRequestDto));
    }

    @GetMapping("/getOrder")
    public ResponseEntity<OrderRequestDto> getOrder(@RequestParam Long id) {
        return ResponseEntity.ok(orderService.getOrder(id));
    }

    @DeleteMapping("/deleteOrder")
    public ResponseEntity<Void> deleteOrder(@RequestParam Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}
