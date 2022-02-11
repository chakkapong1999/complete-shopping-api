/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.service.impl;

import com.service.api.dao.UserDao;
import com.service.api.domain.User;
import com.service.api.model.request.ChangePasswordRequest;
import com.service.api.model.request.UserRequest;
import com.service.api.model.response.ChangePasswordResponse;
import com.service.api.model.response.UserResponse;
import com.service.api.service.UserService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chakkapong
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserResponse createUser(UserRequest request) throws Exception {
        UserResponse response = new UserResponse();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User userInsert = new User();
        Date currentDate = new Date();
        try {
            User checkUser = userDao.findByUsername(request.getUsername());
            if (null != checkUser) {
                response.setSuccess(Boolean.FALSE);
                response.setUsername(request.getUsername());
                response.setMessage("This username is already used.");
            } else {
                userInsert.setCreateBy(request.getUsername());
                userInsert.setCreateDate(currentDate);
                userInsert.setUsername(request.getUsername());
                userInsert.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

                userDao.insert(userInsert);

                response.setSuccess(Boolean.TRUE);
                response.setUsername(request.getUsername());
                response.setMessage("Create user success.");
            }
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request) throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        ChangePasswordResponse response = new ChangePasswordResponse();
        Date currentDate = new Date();
        User updateUser = new User();
        try {
            User user = userDao.findByUsername(request.getUsername());
            if (bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                updateUser.setUsername(request.getUsername());
                updateUser.setPassword(bCryptPasswordEncoder.encode(request.getNewPassword()));
                updateUser.setUpdateDate(currentDate);
                updateUser.setUpdateBy(request.getUsername());
                userDao.update(updateUser);

                response.setSuccess(Boolean.TRUE);
                response.setUsername(request.getUsername());
                response.setMessage("Change Password Success");
            } else {
                response.setSuccess(Boolean.FALSE);
                response.setUsername(request.getUsername());
                response.setMessage("Incorrect Password");
            }
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

}
