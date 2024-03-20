package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.service.ProductOrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderProduct")
public class OrderProductController {

    private final ProductOrderService productOrderService;

    public OrderProductController(ProductOrderService productOrderService) {
        this.productOrderService = productOrderService;
    }

    @GetMapping("/getProductByOrderId")
    public void getProductByOrderId(@RequestParam Long orderId){
        productOrderService.getProductByOrderId(orderId);
    }
}
