/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.api.dao;

import com.service.api.domain.User;

/**
 *
 * @author Chakkapong
 */
public interface UserDao {
    
    User findByUsername (String username) throws Exception;
    void insert (User insertObject) throws Exception;
    void update (User updateObject) throws Exception;
    
}
