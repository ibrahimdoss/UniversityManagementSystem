package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle .product.order.domain.dto.UserUpdateRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateResponseDto;
import com.prodCycle.product.order.exception.BusinessException;
import com.prodCycle.product.order.mapper.UserMapper;
import com.prodCycle.product.order.repository.UserRepository;
import com.prodCycle.product.order.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final SmsService smsService;


    @Override
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

    @Override
    public Optional<UserEntity> findUserById(Long id){
        return userRepository.findById(id);
    }

    @Transactional
    @Override
    public UserUpdateResponseDto updateUser(Long id, UserUpdateRequestDto userUpdateDto) {
        UserEntity existingUser = userRepository.findById(id)
                .orElseThrow(() -> new BusinessException("User not found with id: " + id));

        userMapper.updateUserFromDto(userUpdateDto, existingUser);

        UserEntity updatedUser = userRepository.save(existingUser);

        smsService.sendSmsToUpdateUser(existingUser);

        return userMapper.userEntityToUserUpdateResponseDto(updatedUser);

    }
}
