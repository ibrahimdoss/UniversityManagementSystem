package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;

import java.util.Optional;

public interface UserService {

    void save(UserSaveRequestDto userSaveRequestDto);

    Optional<UserEntity> findUserById(Long id);
}
