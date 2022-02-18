package com.service.api.service.impl;

import com.service.api.dao.UserSessionDao;
import com.service.api.domain.UserSession;
import com.service.api.service.UserSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Chakkapong
 */
@Service
public class UserSessionServiceImpl  {

    @Autowired
    private UserSessionDao userSessionDao;

    public UserSession find(UserSession find) throws Exception {
        UserSession response = new UserSession();
        try {
            response = userSessionDao.find(find);
        } catch (Exception e) {
            throw e;
        }
        return response;
    }
}
