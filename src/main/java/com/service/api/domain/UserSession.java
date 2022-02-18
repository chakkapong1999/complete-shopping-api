package com.service.api.domain;

import java.util.Date;

/**
 * @author Chakkapong
 */
public class UserSession extends BaseDomain{
    private Integer id;
    private String username;
    private String token;
    private Date expireTime;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("UserSession{");
        sb.append("id=").append(id);
        sb.append(", username='").append(username).append('\'');
        sb.append(", token='").append(token).append('\'');
        sb.append(", expireTime=").append(expireTime);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
