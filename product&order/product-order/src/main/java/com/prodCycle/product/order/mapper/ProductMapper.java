package com.prodCycle.product.order.mapper;

import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.dto.ProductDto;
import com.prodCycle.product.order.domain.dto.ProductResponseByCategoryDto;
import com.prodCycle.product.order.domain.dto.ProductSaveRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductDto productEntityToProductDto(ProductEntity product);

    @Mapping(source = "name", target = "name")
    @Mapping(source = "category", target = "category")
    @Mapping(source = "photoUrl", target = "photoUrl")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    @Mapping(source = "numberOfProduct", target = "numberOfProduct")
    ProductEntity productSaveRequestDtoToProductEntity(ProductSaveRequestDto productDto);

    ProductResponseByCategoryDto productToProductResponseDtoByCategory(ProductEntity product);


}
