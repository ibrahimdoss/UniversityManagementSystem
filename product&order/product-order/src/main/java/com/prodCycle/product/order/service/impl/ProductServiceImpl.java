package com.prodCycle.product.order.service.impl;

import com.prodCycle.product.order.domain.ProductEntity;
import com.prodCycle.product.order.domain.dto.ProductDto;
import com.prodCycle.product.order.domain.dto.ProductResponseByCategoryDto;
import com.prodCycle.product.order.domain.dto.ProductSaveRequestDto;
import com.prodCycle.product.order.exception.BusinessException;
import com.prodCycle.product.order.mapper.ProductMapper;
import com.prodCycle.product.order.repository.ProductRepository;
import com.prodCycle.product.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Override
    public void createProduct(ProductSaveRequestDto productDto) {
        ProductEntity productEntity = productMapper.productSaveRequestDtoToProductEntity(productDto);
        productRepository.save(productEntity);
        logger.info("Product saved: {}", productEntity);
    }

    @Override
    public void delete(Long productId){
        ProductEntity productEntity = productRepository.findById(productId).get();
        productRepository.delete(productEntity);
    }

    @Override
    public Optional<List<ProductResponseByCategoryDto>> productListByCategory(String category){
        List<ProductEntity> allByCategory = productRepository.findAllByCategory(category);

        List<ProductResponseByCategoryDto> listMapstruct = allByCategory.stream().map(productMapper::productToProductResponseDtoByCategory).toList();
        return Optional.of(listMapstruct);
    }

    @Override
    public ProductEntity findProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new BusinessException("Product not found for ID: " + productId));
    }

    @Override
    public void decrementProductStock(ProductEntity productEntity) {
        int numberOfProduct = productEntity.getNumberOfProduct();
        productEntity.setNumberOfProduct(--numberOfProduct);
        productRepository.save(productEntity);
    }

    @Override
    public void checkProductStock(ProductEntity productEntity) {
        if (productEntity.getNumberOfProduct() <= 0) {
            throw new BusinessException("Product is out of stock for ID: " + productEntity.getId());
        }
    }

    @Override
    public void saveProduct(ProductEntity productEntity) {
        productRepository.save(productEntity);
    }

    @Override
    public List<ProductEntity> findProductsByIds(List<Long> productIds) {
        return productRepository.findAllById(productIds);
    }


}
