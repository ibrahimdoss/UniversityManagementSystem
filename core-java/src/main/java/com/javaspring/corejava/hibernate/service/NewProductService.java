package com.javaspring.corejava.hibernate.service;

import com.javaspring.corejava.hibernate.domain.ProductEntity;
import com.javaspring.corejava.hibernate.domain.dto.ProductDto;
import com.javaspring.corejava.hibernate.mapper.NewProductMapper;
import com.javaspring.corejava.hibernate.repository.NewProductRepository;
import org.springframework.stereotype.Service;

@Service
public class NewProductService {

    private final NewProductRepository newProductRepository;
    private final NewProductMapper newProductMapper;

    public NewProductService(NewProductRepository newProductRepository, NewProductMapper newProductMapper) {
        this.newProductRepository = newProductRepository;
        this.newProductMapper = newProductMapper;
    }

    public ProductEntity saveProduct(ProductDto productDto) {
        ProductEntity product = newProductMapper.productDTOToProduct(productDto);
        return newProductRepository.save(product);
    }
}
