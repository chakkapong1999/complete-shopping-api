/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.service.impl;

import com.service.api.dao.ProductDao;
import com.service.api.domain.Product;
import com.service.api.model.request.ProductRequest;
import com.service.api.model.request.UpdateProductRequest;
import com.service.api.model.response.ProductResponse;
import com.service.api.model.response.UpdateProductResponse;
import com.service.api.service.ProductService;

import java.util.ArrayList;
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
        List<Product> products = new ArrayList<>();
        try{
            products = productDao.findAll();
        } catch (Exception e) {
            throw e;
        }
        return products;
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
            response.setMessage("Add product success.");
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest request) throws Exception {
        UpdateProductResponse response = new UpdateProductResponse();
        Product updateProduct = new Product();
        Date currentDate = new Date();
        try {
            Product product = productDao.findByName(request.getName());
            if (null != product) {
                updateProduct.setUpdateDate(currentDate);
                updateProduct.setUpdateBy("admin");
                updateProduct.setName(request.getName());
                updateProduct.setPrice(request.getPrice());
                updateProduct.setImage(request.getImage());
                
                productDao.update(updateProduct);
                
                response.setSuccess(Boolean.TRUE);
                response.setName(request.getName());
                response.setMessage("Update Success");
            } else {
                response.setSuccess(Boolean.FALSE);
                response.setName(request.getName());
                response.setMessage("Update not Success");
            }
        } catch (Exception e) {
            throw e;
        }
        return  response;
    }

    @Override
    public ProductResponse deleteProduct(ProductRequest request) throws Exception {
        ProductResponse response = new ProductResponse();
        try{
            response.setMessage("Delete success.");
            response.setName(request.getName());
            response.setSuccess(Boolean.TRUE);
            productDao.delete(request.getName());
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

}
