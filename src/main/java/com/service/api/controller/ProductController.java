/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.controller;

import com.service.api.domain.vo.ProductVO;
import com.service.api.model.request.ProductRequest;
import com.service.api.model.request.UpdateProductRequest;
import com.service.api.model.response.ProductPagingResponse;
import com.service.api.model.response.ProductResponse;
import com.service.api.model.response.UpdateProductResponse;
import com.service.api.service.ProductService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 *
 * @author Chakkapong
 */
@RestController
public class ProductController extends ProductControllerValidator {

    @Autowired
    private ProductService productService;

    @GetMapping("/products/page")
    public ProductPagingResponse getForPaging(@RequestParam (value = "current") Integer currentPage, @RequestParam (value = "limit" ,defaultValue = "8") Integer perPage) throws Exception {
        ProductPagingResponse response = productService.getForPaging(currentPage,perPage);
        return response;
    }

    @DeleteMapping("/products")
    public Object deleteProduct(@RequestBody ProductRequest request) throws Exception {
        deleteProductValidation(request);
        ProductResponse response = productService.deleteProduct(request);
        return response;
    }

    @GetMapping("/products")
    public List<ProductVO> findAll() throws Exception {
        List<ProductVO> response = productService.getAll();
        return response;
    }

    @PostMapping("/products")
    public Object addProduct(@RequestBody ProductRequest request) throws Exception {
        productValidation(request);
        ProductResponse response = productService.addProduct(request);
        return response;
    }

    @PutMapping("/products")
    public Object updateProduct(@RequestBody UpdateProductRequest request) throws Exception {
        productValidation(request);
        UpdateProductResponse response = productService.updateProduct(request);
        return response;
    }

    @GetMapping("/products/download")
    public void downloadProduct(HttpServletResponse response) throws Exception {
        List<ProductVO> data = productService.getAll();
        try {
            Workbook workbook = new XSSFWorkbook();
            writeExcelFile(workbook, data, "All Products");
            response.setHeader("Content-disposition", "attachment; filename=" + "products.csv");
            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            throw e;
        }
    }

    private void writeExcelFile(Workbook workbook, List<ProductVO> list, String sheetName) throws Exception {
        try {

            Sheet sheet = workbook.createSheet(sheetName);
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("No.");
            header.createCell(1).setCellValue("ID");
            header.createCell(2).setCellValue("NAME");
            header.createCell(3).setCellValue("PRICE");
            header.createCell(4).setCellValue("IMAGE");
            int rowNum = 1;
            int idx = 0;
            for (ProductVO i : list) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(idx++);
                row.createCell(1).setCellValue(i.getProductId());
                row.createCell(2).setCellValue(i.getName());
                row.createCell(3).setCellValue(i.getPrice());
                row.createCell(4).setCellValue(i.getImage());
            }
        } catch (Exception e) {
            throw e;
        }
    }

}
