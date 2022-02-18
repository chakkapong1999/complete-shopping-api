package com.service.api.dao;

import com.service.api.domain.UserSession;

/**
 * @author Chakkapong
 */
public interface UserSessionDao {
    UserSession find (UserSession findObject) throws Exception;
    void update(UserSession updateObject) throws Exception;
    void insert (UserSession userSession) throws Exception;
}
