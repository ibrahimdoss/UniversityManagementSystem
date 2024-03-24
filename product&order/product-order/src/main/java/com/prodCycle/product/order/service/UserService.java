package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle.product.order.exception.BusinessException;
import com.prodCycle.product.order.mapper.UserMapper;
import com.prodCycle.product.order.repository.UserRepository;
import org.springframework.dao.DataIntegrityViolationException;
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

    public void save(UserSaveRequestDto userSaveRequestDto) {
        try {
            UserEntity userEntity = userMapper.userSaveRequestDtoToUserEntity(userSaveRequestDto);
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            // Benzersiz kısıt ihlali İÇİM
            throw new BusinessException("A user with the given details already exists");
        } catch (Exception e) {
            // Genel hatalar için
            throw new BusinessException("An error occurred while saving the user");
        }
    }

    public Optional<UserEntity> findUserById(Long id){
        return userRepository.findById(id);
    }
}
