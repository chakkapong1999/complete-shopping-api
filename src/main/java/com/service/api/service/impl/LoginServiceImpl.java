package com.service.api.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.service.api.dao.UserDao;
import com.service.api.dao.UserSessionDao;
import com.service.api.domain.User;
import com.service.api.domain.UserSession;
import com.service.api.model.request.LoginRequest;
import com.service.api.model.response.LoginResponse;
import com.service.api.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @author Chakkapong
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private UserSessionDao userSessionDao;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    private final long EXP_TIME = 60 * 60 * 1000;

    @Override
    public LoginResponse login(LoginRequest request) throws Exception {
        LoginResponse response = new LoginResponse();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        UserSession userSession = new UserSession();
        User user;
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.MINUTE, 10);
        Date expireTime = calendar.getTime();
        try {
            user = userDao.findByUsername(request.getUsername());
            if (null != user) {
                if(bCryptPasswordEncoder.matches(request.getPassword(), user.getPassword())) {
                    String sid = generateSID();
                    UserSession find = new UserSession();
                    find.setUsername(request.getUsername());
                    UserSession userSessionDB = userSessionDao.find(find);
                    if (null != userSessionDB) {
                        userSession.setUpdateDate(currentDate);
                        userSession.setUpdateBy(request.getUsername());
                        userSession.setToken(sid);
                        userSession.setExpireTime(expireTime);
                        userSession.setUsername(request.getUsername());
                        userSessionDao.update(userSession);
                    } else {
                        userSession.setCreateDate(currentDate);
                        userSession.setCreateBy(request.getUsername());
                        userSession.setUsername(request.getUsername());
                        userSession.setToken(sid);
                        userSession.setExpireTime(expireTime);
                        userSessionDao.insert(userSession);
                    }

                    response.setSuccess(Boolean.TRUE);
                    response.setUsername(request.getUsername());
//                    response.setToken(generateToken(user));
                    response.setToken(sid);
                    response.setMessage("Login Success");

                } else {
                    response.setSuccess(Boolean.FALSE);
                    response.setUsername(request.getUsername());
                    response.setToken("");
                    response.setMessage("Invalid Password");
                }
            } else {
                response.setMessage("No username found.");
                response.setSuccess(false);
                response.setUsername(request.getUsername());
                response.setToken("");
            }

        } catch (Exception e) {
            throw e;
        }
        return response;
    }

    private String generateToken(User user) throws Exception{
        String token = "";
        try {
            Date now = new Date();
            long exp = now.getTime() + EXP_TIME;
            token = JWT.create()
                    .withClaim("userId", user.getUserID())
                    .withClaim("username", user.getUsername())
                    .withExpiresAt(new Date(exp))
                    .sign(Algorithm.HMAC256(SECRET_KEY));
        } catch (Exception e) {
            throw e;
        }
        return token;
    }

    private String generateSID() throws Exception {
        UUID uuid = UUID.randomUUID();
        String sid = uuid.toString() + String.valueOf(new Date().getTime());
        return sid;
    }
}
