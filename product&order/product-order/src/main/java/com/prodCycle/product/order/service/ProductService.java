package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.dto.ProductDto;
import com.prodCycle.product.order.mapper.ProductMapper;
import com.prodCycle.product.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = productMapper.productDtoToProductEntity(productDto);
        productEntity = productRepository.save(productEntity);
        return productMapper.productEntityToProductDto(productEntity);
    }
}
