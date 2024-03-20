package com.prodCycle.product.order.mapper;

import com.prodCycle.product.order.domain.UserEntity;
import com.prodCycle.product.order.domain.dto.UserSaveRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserEntity userSaveRequestDtoToUserEntity (UserSaveRequestDto userSaveRequestDto);
}
