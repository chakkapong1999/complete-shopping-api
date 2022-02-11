/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.model.request.ChangePasswordRequest;
import com.service.api.model.request.LoginRequest;
import com.service.api.model.request.UserRequest;
import com.service.api.model.response.ChangePasswordResponse;
import com.service.api.model.response.LoginResponse;
import com.service.api.model.response.UserResponse;
import com.service.api.service.LoginService;
import com.service.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Chakkapong
 */
@RestController
public class UserController extends UserControllerValidator{
    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping("login")
    public Object login(@RequestBody LoginRequest request) throws Exception {
        LoginResponse response = loginService.login(request);
        return response;
    }

    @PostMapping("/create-user")
    public Object createUser (@RequestBody UserRequest request) throws Exception {
        createUserValidation(request);
        UserResponse response = userService.createUser(request);
        return response;
    }
    
    @PostMapping("change-password")
    public Object changePassword(@RequestBody ChangePasswordRequest request) throws Exception {
        changePasswordValidation(request);
        ChangePasswordResponse response = userService.changePassword(request);
        return response;
    }
}
