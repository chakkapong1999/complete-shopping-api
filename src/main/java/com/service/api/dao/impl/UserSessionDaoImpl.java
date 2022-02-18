package com.service.api.dao.impl;

import com.service.api.constant.DatabaseConstant;
import com.service.api.dao.UserSessionDao;
import com.service.api.domain.UserSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chakkapong
 */
@Repository
public class UserSessionDaoImpl implements UserSessionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String TABLE = "user_session";

    private final String CREATE_DATE = "create_date";
    private final String CREATE_BY = "create_by";
    private final String UPDATE_DATE = "update_date";
    private final String UPDATE_BY = "update_by";

    private final String ID = "id";
    private final String USERNAME = "username";
    private final String TOKEN = "token";
    private final String EXPIRE_TIME = "expire_time";

    private final RowMapper<UserSession> ROW_MAPPER = (ResultSet result, int rowNum) ->  {
        UserSession userSession = new UserSession();
        userSession.setCreateDate(result.getTimestamp(CREATE_DATE));
        userSession.setCreateBy(result.getString(CREATE_BY));
        userSession.setUpdateDate(result.getTimestamp(UPDATE_DATE));
        userSession.setUpdateBy(result.getString(UPDATE_BY));

        userSession.setId(result.getInt(ID));
        userSession.setUsername(result.getString(USERNAME));
        userSession.setToken(result.getString(TOKEN));
        userSession.setExpireTime(result.getTimestamp(EXPIRE_TIME));
        return userSession;
    };

    @Override
    public UserSession find(UserSession findObject) throws Exception {
        UserSession userSession = new UserSession();
        List<Object> parameters = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        try {
            sql.append(" select * from ").append(TABLE).append(" where 0 = 0 ");
            if (findObject != null) {
                if (findObject.getCreateDate() != null) {
                    sql.append(DatabaseConstant.AND).append(CREATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateDate());
                }
                if (null != findObject.getCreateBy()) {
                    sql.append(DatabaseConstant.AND).append(CREATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getCreateBy());
                }
                if (null != findObject.getUpdateDate()) {
                    sql.append(DatabaseConstant.AND).append(UPDATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUpdateDate());
                }
                if (null != findObject.getId()) {
                    sql.append(DatabaseConstant.AND).append(ID).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getId());
                }
                if (null != findObject.getUsername()) {
                    sql.append(DatabaseConstant.AND).append(USERNAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getUsername());
                }
                if (null != findObject.getToken()) {
                    sql.append(DatabaseConstant.AND).append(TOKEN).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getToken());
                }
                if (null != findObject.getExpireTime()) {
                    sql.append(DatabaseConstant.AND).append(EXPIRE_TIME).append(DatabaseConstant.EQUAL_QUESTION_MARK);
                    parameters.add(findObject.getExpireTime());
                }
            }

            userSession = jdbcTemplate.queryForObject(sql.toString(), parameters.toArray(), ROW_MAPPER);
        } catch (DataAccessException e) {
            return null;
        } catch (Exception e) {
            throw e;
        }
        return userSession;
    }

    @Override
    public void update(UserSession updateObject) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();
        try {
            sql.append(" update ").append(TABLE).append(" set ");
            if (updateObject != null) {
                sql
                        .append(UPDATE_DATE).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(",")
                        .append(UPDATE_BY).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(",");
                sql
                        .append(TOKEN).append(DatabaseConstant.EQUAL_QUESTION_MARK).append(",")
                        .append(EXPIRE_TIME).append(DatabaseConstant.EQUAL_QUESTION_MARK);

                sql.append(" where ").append(USERNAME).append(DatabaseConstant.EQUAL_QUESTION_MARK);

                parameters.add(updateObject.getUpdateDate());
                parameters.add(updateObject.getUpdateBy());
                parameters.add(updateObject.getToken());
                parameters.add(updateObject.getExpireTime());
                parameters.add(updateObject.getUsername());

                jdbcTemplate.update(sql.toString(), parameters.toArray());
            }
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void insert(UserSession userSession) throws Exception {
        StringBuilder sql = new StringBuilder();
        ArrayList<Object> parameters = new ArrayList<>();
        try {
            sql.append(" insert into ").append(TABLE).append(" (")
                    .append(CREATE_DATE).append(DatabaseConstant.SIGN_COMMA)
                    .append(CREATE_BY).append(DatabaseConstant.SIGN_COMMA);
            sql.append(USERNAME).append(DatabaseConstant.SIGN_COMMA)
                    .append(TOKEN).append(DatabaseConstant.SIGN_COMMA)
                    .append(EXPIRE_TIME).append(")");

            //values
            sql.append(" values ").append(" ( ")
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK).append(DatabaseConstant.SIGN_COMMA)
                    .append(DatabaseConstant.SIGN_QUESTION_MARK)
                    .append(")");

            parameters.add(userSession.getCreateDate());
            parameters.add(userSession.getCreateBy());
            parameters.add(userSession.getUsername());
            parameters.add(userSession.getToken());
            parameters.add(userSession.getExpireTime());
            jdbcTemplate.update(sql.toString(), parameters.toArray());
        } catch (DataAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
}
