/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.service.impl;

import com.service.api.dao.ProductDao;
import com.service.api.domain.Product;
import com.service.api.model.request.ProductRequest;
import com.service.api.model.response.ProductResponse;
import com.service.api.service.ProductService;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chakkapong
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDao productDao;
    
    @Override
    public List<Product> getAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public ProductResponse addProduct(ProductRequest request) throws Exception {
        ProductResponse response = new ProductResponse();
        Date currentDate = new Date();
        Product insertProduct = new Product();
        
        try {
            insertProduct.setCreateBy("admin");
            insertProduct.setCreateDate(currentDate);
            insertProduct.setName(request.getName());
            insertProduct.setPrice(request.getPrice());
            insertProduct.setImage(request.getImage());
            productDao.insert(insertProduct);
            
            response.setSuccess(Boolean.TRUE);
            response.setName(request.getName());
        } catch (Exception e) {
            throw e;
        }
        return response;
    }
    
}
