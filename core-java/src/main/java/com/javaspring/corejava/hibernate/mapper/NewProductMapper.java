package com.javaspring.corejava.hibernate.mapper;

import com.javaspring.corejava.hibernate.domain.ProductEntity;
import com.javaspring.corejava.hibernate.domain.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface NewProductMapper {

    NewProductMapper INSTANCE = Mappers.getMapper(NewProductMapper.class);

    ProductDto productToProductDTO(ProductEntity product);


    ProductEntity productDTOToProduct(ProductDto productDTO);
}
