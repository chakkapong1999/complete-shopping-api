package com.service.api.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.service.api.constant.ExceptionConstant;
import com.service.api.dao.UserSessionDao;
import com.service.api.domain.UserSession;
import com.service.api.exceptions.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Chakkapong
 */
@Component
public class Interceptor implements HandlerInterceptor {

    @Autowired
    private UserSessionDao userSessionDao;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("Pre handle is calling.");

        if(request.getMethod().equals("OPTIONS")) return true;

        if(request.getHeader("Authorization") == null) return false;
        else {
            String token = request.getHeader("Authorization");
            String username = request.getHeader("User");
            try {
                UserSession find = new UserSession();
                find.setUsername(username);
                find.setToken(token);

                UserSession userSession = userSessionDao.find(find);

                if (null == userSession) {
                    throw new ServiceException(ExceptionConstant.TOKEN_NOT_FOUND);
                } else {
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(userSession.getExpireTime());
                    if (new Date().compareTo(calendar.getTime()) == 1) {
                        throw new ServiceException(ExceptionConstant.TOKEN_EXPIRED);
                    }
                }

//                DecodedJWT jwt = JWT.decode(token);
//                String id = jwt.getClaim("userId").asString();
//                String username = jwt.getClaim("username").asString();
//
//                JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET_KEY))
//                        .withClaim("userId", id)
//                        .withClaim("username", username)
//                        .build();
//                verifier.verify(token);
                return true;
            } catch (Exception e) {
                throw e;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception{
        System.out.println("Post handle is calling.");
    }

}
