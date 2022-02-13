/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.dao.impl;

import com.service.api.constant.DatabaseConstant;
import com.service.api.dao.UserDao;
import com.service.api.domain.User;
import java.sql.ResultSet;
import java.util.ArrayList;
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
public class UserDaoImpl implements UserDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "user";

    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";

    private final String USER_ID = "user_id";
    private final String USERNAME = "username";
    private final String PASSWORD = "password";

    private final RowMapper<User> ROW_MAPPER = (ResultSet result, int rowNum) -> {
        User user = new User();
        user.setCreateDate(result.getTimestamp(CREATE_DATE));
        user.setCreateBy(result.getString(CREATE_BY));
        user.setUpdateDate(result.getTimestamp(UPDATE_DATE));
        user.setUpdateBy(result.getString(UPDATE_BY));

        user.setUserID(result.getInt(USER_ID));
        user.setUsername(result.getString(USERNAME));
        user.setPassword(result.getString(PASSWORD));
        return user;
    };

    @Override
    public User findByUsername(String username) throws Exception {
        StringBuilder sql = new StringBuilder();
        User user = new User();
        try {
            sql.append(" select * from ").append(TABLE).append(DatabaseConstant.WHERE).append(USERNAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
            user = jdbcTemplate.queryForObject(sql.toString(), ROW_MAPPER, username);
        } catch (DataAccessException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
        return user;
    }

    @Override
    public void insert(User insertObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();
        try {
            //setup column
            sql.append(" insert into ").append(TABLE).append(" (")
                    .append(CREATE_DATE).append(DatabaseConstant.SIGN_COMMA)
                    .append(CREATE_BY).append(DatabaseConstant.SIGN_COMMA);

            sql.append(USERNAME).append(DatabaseConstant.SIGN_COMMA)
                    .append(PASSWORD)
                    .append(")");

            //value;
            sql.append(" values ").append(" ( ")
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK)
                    .append(")");

            parameters.add(insertObject.getCreateDate());
            parameters.add(insertObject.getCreateBy());
            parameters.add(insertObject.getUsername());
            parameters.add(insertObject.getPassword());

            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void update(User updateObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();

        try {
            sql.append(" update ").append(TABLE).append(" set ")
                    .append(UPDATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(UPDATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(PASSWORD).append(DatabaseConstant.EQUAL_QUESTION_MARK)
                    .append(DatabaseConstant.WHERE)
                    .append(USERNAME)
                    .append(DatabaseConstant.EQUAL_QUESTION_MARK);

            parameters.add(updateObject.getUpdateDate());
            parameters.add(updateObject.getUpdateBy());
            parameters.add(updateObject.getPassword());
            parameters.add(updateObject.getUsername());
            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

}
