package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.dto.ProductDto;
import com.prodCycle.product.order.domain.dto.ProductResponseByCategoryDto;
import com.prodCycle.product.order.domain.dto.ProductSaveRequestDto;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void createProduct(ProductSaveRequestDto productDto);
    void delete(Long productId);

    Optional<List<ProductResponseByCategoryDto>> productListByCategory(String category);

    ProductEntity findProductById(Long productId);

    void decrementProductStock(ProductEntity productEntity);

    void checkProductStock(ProductEntity productEntity);

    void saveProduct(ProductEntity productEntity);

    List<ProductEntity> findProductsByIds(List<Long> productIds);
}
