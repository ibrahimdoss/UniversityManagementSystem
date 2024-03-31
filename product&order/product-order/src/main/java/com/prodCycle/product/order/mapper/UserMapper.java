package com.prodCycle.product.order.mapper;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "name", target = "name")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "email", target = "email")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "isActive", target = "isActive")
    UserEntity userSaveRequestDtoToUserEntity (UserSaveRequestDto userSaveRequestDto);
}
