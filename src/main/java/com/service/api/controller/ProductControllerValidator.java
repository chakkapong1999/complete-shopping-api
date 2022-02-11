/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.model.request.ProductRequest;
import com.service.api.model.request.UpdateProductRequest;
import com.service.api.utils.ObjectValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Chakkapong
 */
public class ProductControllerValidator {

    @Autowired
    private ObjectValidatorUtils validator;

    protected void productValidation(ProductRequest request) throws Exception {
        if (!validator.validateMandatory(request.getName())) {
            throw new Exception("Name of Product is null");
        }
        if (!validator.validateMandatory(request.getPrice().toString())) {
            throw new Exception("Price of Product is null");
        }
        if (!validator.validateMandatory(request.getImage())) {
            throw new Exception("Image of Product is null");
        }
    }

    protected void deleteProductValidation(ProductRequest request) throws Exception {
        if (!validator.validateMandatory(request.getName())) {
            throw new Exception("Name of Product is null");
        }
    }

}
