package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateResponseDto;
import com.prodCycle.product.order.service.UserService;
import com.prodCycle.product.order.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{id}")
    public ResponseEntity<UserUpdateResponseDto> update(@PathVariable Long id, @RequestBody UserUpdateRequestDto userUpdateDto){
        UserUpdateResponseDto userUpdateResponseDto = userService.updateUser(id, userUpdateDto);
        if (userUpdateResponseDto != null) {
            return ResponseEntity.ok(userUpdateResponseDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
