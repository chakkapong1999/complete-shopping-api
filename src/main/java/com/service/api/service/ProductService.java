/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.service.api.service;

import com.service.api.domain.vo.ProductVO;
import com.service.api.model.request.ProductRequest;
import com.service.api.model.request.UpdateProductRequest;
import com.service.api.model.response.ProductPagingResponse;
import com.service.api.model.response.ProductResponse;
import com.service.api.model.response.UpdateProductResponse;
import java.util.List;

/**
 *
 * @author Chakkapong
 */
public interface ProductService {
    List<ProductVO> getAll() throws Exception;
    ProductPagingResponse getForPaging(Integer currentPage, Integer perPage) throws Exception;
    ProductResponse addProduct(ProductRequest request) throws Exception;
    UpdateProductResponse updateProduct(UpdateProductRequest request) throws Exception;
    ProductResponse deleteProduct(ProductRequest request) throws Exception;
}
