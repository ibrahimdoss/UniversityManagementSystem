package com.prodCycle.product.order.mapper;

import com.prodCycle.product.order.domain.OrderEntity;
import com.prodCycle.product.order.domain.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(target = "productId", source = "product.id")
    OrderDto orderEntityToOrderDto(OrderEntity order);
    OrderEntity orderDtoToOrderEntity(OrderDto orderDto);


}
