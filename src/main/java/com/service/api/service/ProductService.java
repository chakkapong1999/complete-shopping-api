/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.api.service;

import com.service.api.domain.Product;
import com.service.api.model.request.ProductRequest;
import com.service.api.model.response.ProductResponse;
import java.util.List;

/**
 *
 * @author Chakkapong
 */
public interface ProductService {
    List<Product> getAll() throws Exception;
    ProductResponse addProduct(ProductRequest request) throws Exception;
}