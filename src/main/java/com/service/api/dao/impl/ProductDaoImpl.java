/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.dao.impl;

import com.service.api.constant.DatabaseConstant;
import com.service.api.dao.ProductDao;
import com.service.api.domain.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Chakkapong
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate JdbcTemplate;

    private final String TABLE = "product";

    private final String CREATE_DATE = "createDate";
    private final String CREATE_BY = "createBy";
    private final String UPDATE_DATE = "updateDate";
    private final String UPDATE_BY = "updateBy";

    private final String PRODUCT_ID = "productId";
    private final String NAME = "name";
    private final String PRICE = "price";
    private final String IMAGE = "image";

    private RowMapper<Product> ROW_MAPPER = (ResultSet result, int rowNum) -> {
        Product product = new Product();
        product.setCreateDate(result.getTimestamp(CREATE_DATE));
        product.setCreateBy(result.getString(CREATE_BY));
        product.setUpdateDate(result.getTimestamp(UPDATE_DATE));
        product.setUpdateBy(result.getString(UPDATE_BY));

        product.setProductId(result.getInt(PRODUCT_ID));
        product.setName(result.getString(NAME));
        product.setPrice(result.getInt(PRICE));
        product.setImage(result.getString(IMAGE));

        return product;
    };

    @Override

    public List<Product> findAll() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Product insertObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();
        try {
            //setup column
            sql.append(" insert into ").append(TABLE).append(" (")
                    .append(CREATE_DATE).append(DatabaseConstant.SIGN_COMMA)
                    .append(CREATE_BY).append(DatabaseConstant.SIGN_COMMA);

            sql.append(NAME).append(DatabaseConstant.SIGN_COMMA)
                    .append(PRICE).append(DatabaseConstant.SIGN_COMMA)
                    .append(IMAGE)
                    .append(")");
            //values
            sql.append(" values ").append(" ( ")
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK)
                    .append(")");

            parameters.add(insertObject.getCreateDate());
            parameters.add(insertObject.getCreateBy());
            parameters.add(insertObject.getName());
            parameters.add(insertObject.getPrice());
            parameters.add(insertObject.getImage());

            JdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(Product updateObject) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
