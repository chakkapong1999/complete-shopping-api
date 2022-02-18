/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.api.dao;

import com.service.api.domain.Product;
import com.service.api.domain.vo.ProductVO;

import java.util.List;

/**
 *
 * @author Chakkapong
 */
public interface ProductDao {
    
    List<ProductVO> findAll() throws Exception;
    List<ProductVO> findPaging(Integer currentPage, Integer perPage) throws Exception;
    Product findByName(String name) throws Exception;
    void insert (Product insertObject) throws Exception;
    void update (Product updateObject) throws Exception;
    void delete (String name) throws Exception;
    int count() throws Exception;
}
