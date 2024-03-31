package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle.product.order.service.UserService;
import com.prodCycle.product.order.service.impl.UserServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserServiceImpl userServiceImpl) {
        this.userService = userServiceImpl;
    }

    @PostMapping("/save")
    public void save(@RequestBody UserSaveRequestDto userSaveRequestDto){
        userService.save(userSaveRequestDto);
    }
}
