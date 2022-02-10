/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.model.response;

/**
 *
 * @author Chakkapong
 */
public class LoginResponse {
    
    private Boolean success;
    private String username;
    private String token;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LoginResponse{");
        sb.append("success=").append(success);
        sb.append(", username=").append(username);
        sb.append(", token=").append(token);
        sb.append('}');
        return sb.toString();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
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
    
}
