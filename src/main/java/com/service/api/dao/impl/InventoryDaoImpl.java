package com.service.api.dao.impl;

import com.service.api.constant.DatabaseConstant;
import com.service.api.dao.InventoryDao;
import com.service.api.domain.Inventory;
import com.service.api.model.request.InventoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chakkapong
 */
@Repository
public class InventoryDaoImpl implements InventoryDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "inventory";

    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";

    private final String ID = "product_id";
    private final String QUANTITY = "quantity";

    private final RowMapper<Inventory> ROW_MAPPER = (ResultSet result, int rowNum) -> {
        Inventory inventory = new Inventory();

        inventory.setCreateDate(result.getTimestamp(CREATE_DATE));
        inventory.setCreateBy(result.getString(CREATE_BY));
        inventory.setUpdateDate(result.getTimestamp(UPDATE_DATE));
        inventory.setUpdateBy(result.getString(UPDATE_BY));

        inventory.setProductId(result.getInt(ID));
        inventory.setQuantity(result.getInt(QUANTITY));
        return inventory;
    };

    @Override
    public Inventory findById(Integer id) throws Exception {
        Inventory inventory = new Inventory();
        StringBuilder sql = new StringBuilder();
        try {
            sql.append(" select * from ").append(TABLE).append(DatabaseConstant.WHERE)
                    .append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
            inventory = jdbcTemplate.queryForObject(sql.toString(), ROW_MAPPER, id);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
        return inventory;
    }

    @Override
    public void update(Inventory updateObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {
            if (updateObject != null) {
                sql.append(" update ").append(TABLE).append(" set ")
                        .append(UPDATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                        .append(UPDATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                        .append(QUANTITY).append(DatabaseConstant.EQUAL_QUESTION_MARK)
                        .append(DatabaseConstant.WHERE)
                        .append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);

                parameters.add(updateObject.getUpdateDate());
                parameters.add(updateObject.getUpdateBy());
                parameters.add(updateObject.getQuantity());
                parameters.add(updateObject.getProductId());
            }

            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void insert(Inventory insertObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();
        try {
            if(insertObject != null) {
                sql.append(" insert into ").append(TABLE).append(" (")
                    .append(CREATE_DATE).append(DatabaseConstant.SIGN_COMMA)
                        .append(CREATE_BY).append(DatabaseConstant.SIGN_COMMA)
                        .append(ID).append(DatabaseConstant.SIGN_COMMA)
                        .append(QUANTITY).append(")");

                //values
                sql.append(" values ")
                        .append(" ( ")
                        .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                        .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                        .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                        .append(DatabaseConstant.SIGN_QUESTION_MARK)
                        .append(")");

                parameters.add(insertObject.getCreateDate());
                parameters.add(insertObject.getCreateBy());
                parameters.add(insertObject.getProductId());
                parameters.add(insertObject.getQuantity());
            }

            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void delete(Integer id) throws Exception {
        StringBuilder sql = new StringBuilder();
        try {
            sql.append(" delete from ").append(TABLE)
                    .append(DatabaseConstant.WHERE)
                    .append(ID)
                    .append(DatabaseConstant.EQUAL_QUESTION_MARK);

            jdbcTemplate.update(sql.toString(), id);
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }

    }
}
