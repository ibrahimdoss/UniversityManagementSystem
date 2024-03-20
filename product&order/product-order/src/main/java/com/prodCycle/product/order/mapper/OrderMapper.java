package com.prodCycle.product.order.mapper;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.dto.OrderRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderRequestDto orderEntityToOrderDto(OrderEntity order);
    OrderEntity orderDtoToOrderEntity(OrderRequestDto orderRequestDto);


}
