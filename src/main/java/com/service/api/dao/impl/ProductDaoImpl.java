/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.dao.impl;

import com.service.api.constant.DatabaseConstant;
import com.service.api.constant.ExceptionConstant;
import com.service.api.dao.ProductDao;
import com.service.api.domain.Product;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.service.api.domain.vo.ProductVO;
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

    private final String QUANTITY = "quantity";

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

    private final RowMapper<ProductVO> ROW_MAPPER_VO = (ResultSet result, int rowNum) -> {
        ProductVO productVO = new ProductVO();
        productVO.setProductId(result.getInt(PRODUCT_ID));
        productVO.setName(result.getString(NAME));
        productVO.setPrice(result.getInt(PRICE));
        productVO.setImage(result.getString(IMAGE));
        productVO.setQuantity(result.getInt(QUANTITY));
        return productVO;
    };

    @Override
    public List<ProductVO> findAll() throws Exception {
        StringBuilder sql = new StringBuilder();
        List<ProductVO> productVO = new ArrayList<>();
        try {
            sql.append(DatabaseConstant.SELECT)
                    .append(" product.product_id, product.name, product.price, product.image, inventory.quantity ")
                    .append(DatabaseConstant.FROM).append(TABLE).append(" left join inventory ")
                    .append(" on ").append(" product.product_id = inventory.product_id");
            productVO = jdbcTemplate.query(sql.toString(), ROW_MAPPER_VO);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return productVO;
    }

    @Override
    public List<ProductVO> findPaging(Integer currentPage, Integer perPage) throws Exception {
        StringBuilder sql = new StringBuilder();
        List<ProductVO> productVO = new ArrayList<>();
        try {
            sql.append(DatabaseConstant.SELECT)
                    .append(" product.product_id, product.name, product.price, product.image, inventory.quantity ")
                    .append(DatabaseConstant.FROM).append(TABLE).append(" left join inventory ")
                    .append(" on ").append(" product.product_id = inventory.product_id")
                    .append(DatabaseConstant.ORDER_BY).append(PRODUCT_ID).append(DatabaseConstant.ASC)
                    .append(" limit ").append(DatabaseConstant.SIGN_QUESTION_MARK)
                    .append(" offset ").append(DatabaseConstant.SIGN_QUESTION_MARK);
            productVO = jdbcTemplate.query(sql.toString(), ROW_MAPPER_VO, perPage, currentPage);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return productVO;
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
            throw new DatabaseException(ExceptionConstant.DATABASE_CANNOT_DELETE);
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
            throw new DatabaseException(ExceptionConstant.DATABASE_NOT_FOUND);
        }
        catch (Exception e) {
            throw e;
        }
        return product;
    }

}
