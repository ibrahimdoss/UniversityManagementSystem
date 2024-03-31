package com.prodCycle.product.order.mapper;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateRequestDto;
import com.prodCycle.product.order.domain.dto.UserUpdateResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {


    UserEntity userSaveRequestDtoToUserEntity (UserSaveRequestDto userSaveRequestDto);


    UserEntity userUpdateRequestDtoToUserEntity(UserUpdateRequestDto userUpdateRequestDto);

    UserUpdateResponseDto userEntityToUserUpdateResponseDto(UserEntity userEntity);

    @Mapping(target = "id", ignore = true)
    void updateUserFromDto(UserUpdateRequestDto dto, @MappingTarget UserEntity entity);

}
