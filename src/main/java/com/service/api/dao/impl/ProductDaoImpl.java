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

import com.service.api.exceptions.DatabaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;

/**
 *
 * @author Chakkapong
 */
@Repository
public class ProductDaoImpl implements ProductDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "product";

    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";

    private final String PRODUCT_ID = "product_id";
    private final String NAME = "name";
    private final String PRICE = "price";
    private final String IMAGE = "image";

    private final RowMapper<Product> ROW_MAPPER = (ResultSet result, int rowNum) -> {
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
        StringBuilder sql = new StringBuilder();
        List<Product> products = new ArrayList<>();
        try {
            sql.append(" select * from ").append(TABLE).append(DatabaseConstant.WHERE_0_EQUAL_0);
            products = jdbcTemplate.query(sql.toString(),ROW_MAPPER);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return products;
    }

    @Override
    public List<Product> findPaging(Integer currentPage, Integer perPage) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<Product> products = new ArrayList<>();
        try {
            sql.append(" select * from ").append(TABLE)
                    .append(DatabaseConstant.ORDER_BY).append(PRODUCT_ID)
                    .append(" limit ").append(DatabaseConstant.SIGN_QUESTION_MARK)
                    .append(" offset ").append(DatabaseConstant.SIGN_QUESTION_MARK);
            products = jdbcTemplate.query(sql.toString(), ROW_MAPPER, perPage, currentPage);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return products;
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

            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(Product updateObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();
        try {
            sql.append(" update ").append(TABLE).append(" set ")
                    .append(UPDATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(UPDATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(PRICE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(IMAGE).append(DatabaseConstant.EQUAL_QUESTION_MARK)
                    .append(DatabaseConstant.WHERE)
                    .append(NAME)
                    .append(DatabaseConstant.EQUAL_QUESTION_MARK);

            parameters.add(updateObject.getUpdateDate());
            parameters.add(updateObject.getUpdateBy());
            parameters.add(updateObject.getName());
            parameters.add(updateObject.getPrice());
            parameters.add(updateObject.getImage());
            parameters.add(updateObject.getName());

            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(String name) throws Exception {
        StringBuilder sql = new StringBuilder();
        try{
            sql.append(" delete from ").append(TABLE).append(DatabaseConstant.WHERE).append(NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
            jdbcTemplate.update(sql.toString(),name);
        } catch (DataAccessException e) {
            throw new DatabaseException("201");
        } catch (Exception e) {
            throw e;
        }


    }

    @Override
    public int count() throws Exception {
        int count;
        StringBuilder sql = new StringBuilder();
        try {
            sql.append(" select count(*) from ").append(TABLE);
            count = jdbcTemplate.queryForObject(sql.toString(), Integer.class);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return count;
    }

    @Override
    public Product findByName(String name) throws Exception {
        Product product = new Product();
        StringBuilder sql = new StringBuilder();
        try {
            sql.append(" select * from ").append(TABLE).append(DatabaseConstant.WHERE)
                    .append(NAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
            product = jdbcTemplate.queryForObject(sql.toString(), ROW_MAPPER, name);
        } catch (DataAccessException e) {
            throw new DatabaseException("200");
        }
        catch (Exception e) {
            throw e;
        }
        return product;
    }

}
