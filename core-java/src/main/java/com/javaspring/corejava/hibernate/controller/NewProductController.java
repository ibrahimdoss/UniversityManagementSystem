package com.javaspring.corejava.hibernate.controller;

import com.javaspring.corejava.hibernate.domain.dto.ProductDto;
import com.javaspring.corejava.hibernate.service.NewProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/newProducts")
public class NewProductController {

    private final NewProductService newProductService;

    public NewProductController(NewProductService newProductService) {
        this.newProductService = newProductService;
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto productDto){
        newProductService.saveProduct(productDto);
        return ResponseEntity.ok("İşleminiz başarılı bir şekilde gerçekleştirildi.");
    }
}
