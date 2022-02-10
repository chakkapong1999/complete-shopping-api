/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.model.request.ChangePasswordRequest;
import com.service.api.model.request.UserRequest;
import com.service.api.utils.ObjectValidatorUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Chakkapong
 */
public class UserControllerValidator {

    @Autowired
    private ObjectValidatorUtils validator;

    protected void createUserValidation(UserRequest request) throws Exception {
        if (!validator.validateMandatory(request.getUsername())) {
            throw new Exception("Username is null");
        }
        if (!validator.validateMandatory(request.getPassword())) {
            throw new Exception("Password is null");
        }
    }

    protected void changePasswordValidation(ChangePasswordRequest request) throws Exception {
        if (!validator.validateMandatory(request.getUsername())) {
            throw new Exception("Username is null");
        }
        if (!validator.validateMandatory(request.getPassword())) {
            throw new Exception("Password is null");
        }
        if (!validator.validateMandatory(request.getNewPassword())) {
            throw new Exception("New Password is null");
        }
    }

}
