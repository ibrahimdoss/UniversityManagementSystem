package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle.product.order.mapper.UserMapper;
import com.prodCycle.product.order.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void save(UserSaveRequestDto userSaveRequestDto){
        UserEntity userEntity = userMapper.userSaveRequestDtoToUserEntity(userSaveRequestDto);
        userRepository.save(userEntity);
    }

    public Optional<UserEntity> findUserById(Long id){
        return userRepository.findById(id);
    }
}
