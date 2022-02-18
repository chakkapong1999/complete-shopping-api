package com.service.api.service;

import com.service.api.domain.UserSession;

/**
 * @author Chakkapong
 */
public interface UserSessionService {
    UserSession find(UserSession findObject) throws Exception;
}
