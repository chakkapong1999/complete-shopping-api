/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.model.request.ProductRequest;
import com.service.api.model.request.UpdateProductRequest;
import com.service.api.model.response.ProductResponse;
import com.service.api.model.response.UpdateProductResponse;
import com.service.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chakkapong
 */
@RestController
public class ProductController extends ProductControllerValidator {

    @Autowired
    private ProductService productService;

    @PostMapping("/products")
    public Object addProduct(@RequestBody ProductRequest request) throws Exception {
        productValidation(request);
        ProductResponse response = productService.addProduct(request);
        return response;
    }

    @PutMapping("/products")
    public Object updateProduct(@RequestBody UpdateProductRequest request) throws Exception {
        productValidation(request);
        UpdateProductResponse response = productService.updateProduct(request);
        return response;
    }
}
