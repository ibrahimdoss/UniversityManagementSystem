package com.prodCycle.product.order.controller;

import com.prodCycle.product.order.domain.dto.ProductDto;
import com.prodCycle.product.order.domain.dto.ProductResponseByCategoryDto;
import com.prodCycle.product.order.domain.dto.ProductSaveRequestDto;
import com.prodCycle.product.order.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/create")
    public void createProduct(@RequestBody ProductSaveRequestDto productDto) {
        productService.createProduct(productDto);
    }

    @DeleteMapping
    public void delete(@RequestParam Long productId){
        productService.delete(productId);
    }

    @GetMapping("/productListByCategory/{category}")
    public ResponseEntity<List<ProductResponseByCategoryDto>> productListByCategory(@PathVariable String category){
        Optional<List<ProductResponseByCategoryDto>> productResponseByCategoryDtos = productService.productListByCategory(category);

        if (productResponseByCategoryDtos.isPresent()){
            List<ProductResponseByCategoryDto> responseByCategoryDtos = productResponseByCategoryDtos.get();
            return ResponseEntity.ok(responseByCategoryDtos);
        }
        return ResponseEntity.notFound().build();
    }
}
