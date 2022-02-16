package com.service.api.controller;

import com.service.api.constant.ExceptionConstant;
import com.service.api.exceptions.ServiceValidation;
import com.service.api.model.request.InventoryRequest;
import com.service.api.utils.ObjectValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chakkapong
 */
public class InventoryControllerValidator {

    @Autowired
    private ObjectValidatorUtils validator;

    protected void updateInventoryValidation(InventoryRequest request) throws Exception {
        if(null == request.getId()) throw new ServiceValidation(ExceptionConstant.REQUIRED);
    }
}
