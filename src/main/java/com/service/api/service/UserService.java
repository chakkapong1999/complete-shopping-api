/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.api.service;

import com.service.api.model.request.ChangePasswordRequest;
import com.service.api.model.request.UserRequest;
import com.service.api.model.response.ChangePasswordResponse;
import com.service.api.model.response.UserResponse;

/**
 *
 * @author Chakkapong
 */
public interface UserService {
    
    UserResponse createUser(UserRequest request) throws Exception;
    ChangePasswordResponse changePassword (ChangePasswordRequest request) throws Exception;
    
}
