package com.prodCycle.product.order.service;

import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.dto.ProductDto;
import com.prodCycle.product.order.domain.dto.ProductResponseByCategoryDto;
import com.prodCycle.product.order.mapper.ProductMapper;
import com.prodCycle.product.order.repository.OrderProductRepository;
import com.prodCycle.product.order.repository.OrderRepository;
import com.prodCycle.product.order.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductEntityRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductDto createProduct(ProductDto productDto) {
        ProductEntity productEntity = productMapper.productDtoToProductEntity(productDto);
        productEntity = productRepository.save(productEntity);
        return productMapper.productEntityToProductDto(productEntity);
    }

    public ProductEntity getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + productId));
    }

    public void delete(Long productId){
        ProductEntity productEntity = productRepository.findById(productId).get();
        productRepository.delete(productEntity);
    }

    public Optional<List<ProductResponseByCategoryDto>> productListByCategory(String category){
        List<ProductEntity> allByCategory = productRepository.findAllByCategory(category);

        List<ProductResponseByCategoryDto> listMapstruct = allByCategory.stream().map(productMapper::productToProductResponseDtoByCategory).toList();
        return Optional.of(listMapstruct);
    }








}
