/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.api.dao;

import com.service.api.domain.Product;
import java.util.List;

/**
 *
 * @author Chakkapong
 */
public interface ProductDao {
    
    List<Product> findAll() throws Exception;
    void insert (Product insertObject) throws Exception;
    void update (Product updateObject) throws Exception;
}
