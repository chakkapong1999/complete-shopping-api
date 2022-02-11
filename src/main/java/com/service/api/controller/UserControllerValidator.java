/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.constant.ExceptionConstant;
import com.service.api.model.request.ChangePasswordRequest;
import com.service.api.model.request.LoginRequest;
import com.service.api.model.request.UserRequest;
import com.service.api.utils.ObjectValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Chakkapong
 */
public class UserControllerValidator {

    @Autowired
    private ObjectValidatorUtils validator;

    protected void createUserValidation(UserRequest request) throws Exception {
        if (!validator.validateMandatory(request.getUsername())
                || !validator.validateMandatory(request.getPassword())) {
            throw new Exception(ExceptionConstant.REQUIRED);
        }
    }

    protected void changePasswordValidation(ChangePasswordRequest request) throws Exception {
        if (!validator.validateMandatory(request.getUsername())
                || !validator.validateMandatory(request.getPassword())
                || !validator.validateMandatory(request.getNewPassword())) {
            throw new Exception(ExceptionConstant.REQUIRED);
        }

    }

    protected void loginValidation(LoginRequest request) throws Exception {
        if (!validator.validateMandatory(request.getUsername()) || !validator.validateMandatory(request.getPassword())) {
            throw new Exception(ExceptionConstant.REQUIRED);
        }
    }

}
