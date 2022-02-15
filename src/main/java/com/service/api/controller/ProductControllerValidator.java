/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.constant.ExceptionConstant;
import com.service.api.exceptions.ServiceValidation;
import com.service.api.model.request.ProductRequest;
import com.service.api.utils.ObjectValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chakkapong
 */
public class ProductControllerValidator {

    @Autowired
    private ObjectValidatorUtils validator;

    protected void productValidation(ProductRequest request) throws Exception {
        if (!validator.validateMandatory(request.getName())
                || !validator.validateMandatory(request.getPrice().toString())
                || !validator.validateMandatory(request.getImage())
        ) {
            throw new ServiceValidation(ExceptionConstant.REQUIRED);
        }
    }

    protected void deleteProductValidation(ProductRequest request) throws Exception {
        if (!validator.validateMandatory(request.getName())) {
            throw new ServiceValidation(ExceptionConstant.REQUIRED);
        }
    }

}
