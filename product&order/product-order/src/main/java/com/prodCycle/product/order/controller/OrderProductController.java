package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.service.impl.OrderProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {

    private final OrderProductService orderProductService;

    public OrderProductController(OrderProductService orderProductService) {
        this.orderProductService = orderProductService;
    }

    @GetMapping("/getProductByOrderId")
    public void getProductByOrderId(@RequestParam Long orderId){
      orderProductService.getProductsByOrderId(orderId);
    }
}
