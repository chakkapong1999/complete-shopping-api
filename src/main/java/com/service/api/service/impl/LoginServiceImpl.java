package com.service.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.service.api.dao.UserDao;
import com.service.api.domain.User;
import com.service.api.model.request.LoginRequest;
import com.service.api.model.response.LoginResponse;
import com.service.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Chakkapong
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        LoginResponse response = new LoginResponse();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User user;
        try {
            user = userDao.findByUsername(request.getUsername());
            if(bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                response.setSuccess(Boolean.TRUE);
                response.setUsername(request.getUsername());
                response.setToken(generateToken(user));
                response.setMessage("Login Success");
            } else {
                response.setSuccess(Boolean.FALSE);
                response.setUsername(request.getUsername());
                response.setToken("");
                response.setMessage("Invalid Password");
            }
        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    private String generateToken(User user) throws Exception{
        String token = "";
        try {
            token = JWT.create()
                    .withClaim("userId", user.getUserID())
                    .withClaim("username", user.getUsername())
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception e) {
            throw e;
        }
        return token;
    }
}