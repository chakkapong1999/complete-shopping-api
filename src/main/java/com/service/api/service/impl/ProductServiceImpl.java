/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.service.impl;

import com.service.api.dao.InventoryDao;
import com.service.api.dao.ProductDao;
import com.service.api.domain.Inventory;
import com.service.api.domain.Product;
import com.service.api.domain.vo.ProductVO;
import com.service.api.model.request.ProductRequest;
import com.service.api.model.request.UpdateProductRequest;
import com.service.api.model.response.ProductResponse;
import com.service.api.model.response.UpdateProductResponse;
import com.service.api.service.ProductService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Chakkapong
 */
@Service
public class ProductServiceImpl implements ProductService {
    
    @Autowired
    private ProductDao productDao;

    @Autowired
    private InventoryDao inventoryDao;

    @Override
    public List<ProductVO> getAll() throws Exception {
        List<ProductVO> response = new ArrayList<>();
        try{
            List<Product> products = productDao.findAll();
            for (Product i : products) {
                ProductVO productVO = new ProductVO();
                productVO.setProductId(i.getProductId());
                productVO.setName(i.getName());
                productVO.setPrice(i.getPrice());
                productVO.setImage(i.getImage());
                response.add(productVO);
            }
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public List<ProductVO> getForPaging(Integer currentPage, Integer perPage) throws Exception {
        List<ProductVO> response = new ArrayList<>();
        Integer page = currentPage == 0 ? 0 : (currentPage - 1) * perPage;
        try {
            List<Product> productsDB = productDao.findPaging(page, perPage);
            for (Product i : productsDB) {
                ProductVO productVO = new ProductVO();
                productVO.setProductId(i.getProductId());
                productVO.setName(i.getName());
                productVO.setPrice(i.getPrice());
                productVO.setImage(i.getImage());
                response.add(productVO);
            }
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public ProductResponse addProduct(ProductRequest request) throws Exception {
        Inventory inventory = new Inventory();
        ProductResponse response = new ProductResponse();
        Date currentDate = new Date();
        Product insertProduct = new Product();
        
        try {
            insertProduct.setCreateBy("admin");
            insertProduct.setCreateDate(currentDate);
            insertProduct.setName(request.getName());
            insertProduct.setPrice(request.getPrice());
            insertProduct.setImage(request.getImage());
            productDao.insert(insertProduct);

            Product find = productDao.findByName(insertProduct.getName());
            inventory.setCreateBy("admin");
            inventory.setCreateDate(currentDate);
            inventory.setProductId(find.getProductId());
            inventory.setQuantity(20);
            inventoryDao.insert(inventory);
            
            response.setSuccess(Boolean.TRUE);
            response.setName(request.getName());
            response.setMessage("Add product success.");
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    @Override
    public UpdateProductResponse updateProduct(UpdateProductRequest request) throws Exception {
        UpdateProductResponse response = new UpdateProductResponse();
        Product updateProduct = new Product();
        Date currentDate = new Date();
        try {
            Product product = productDao.findByName(request.getName());
            if (null != product) {
                updateProduct.setUpdateDate(currentDate);
                updateProduct.setUpdateBy("admin");
                updateProduct.setName(request.getName());
                updateProduct.setPrice(request.getPrice());
                updateProduct.setImage(request.getImage());
                
                productDao.update(updateProduct);
                
                response.setSuccess(Boolean.TRUE);
                response.setName(request.getName());
                response.setMessage("Update Success");
            }
        } catch (Exception e) {
            response.setSuccess(Boolean.FALSE);
            response.setName(request.getName());
            response.setMessage("Error while update product.");
        }
        return  response;
    }

    @Override
    public ProductResponse deleteProduct(ProductRequest request) throws Exception {
        ProductResponse response = new ProductResponse();
        try{
            Product delete = productDao.findByName(request.getName());
            inventoryDao.delete(delete.getProductId());
            productDao.delete(request.getName());

            response.setMessage("Delete success.");
            response.setName(request.getName());
            response.setSuccess(Boolean.TRUE);
        } catch (Exception e) {
            response.setMessage("Error while delete product.");
            response.setName(request.getName());
            response.setSuccess(Boolean.FALSE);
        }
        return response;
    }

}
