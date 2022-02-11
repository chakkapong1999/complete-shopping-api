package com.service.api.service;

import com.service.api.model.request.LoginRequest;
import com.service.api.model.response.LoginResponse;

/**
 * @author Chakkapong
 */
public interface LoginService {
    LoginResponse login(LoginRequest request) throws Exception;
}
