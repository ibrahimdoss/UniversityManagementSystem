package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateResponseDto;

import java.util.Optional;

public interface UserService {

    void save(UserSaveRequestDto userSaveRequestDto);

    Optional<UserEntity> findUserById(Long id);

    UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateDto);
}
