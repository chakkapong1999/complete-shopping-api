/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.model.response;

/**
 *
 * @author Chakkapong
 */
public class UserResponse extends BaseResponse{
    
    private Boolean success;
    private String username;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UserResponse{");
        sb.append("success=").append(success);
        sb.append(", username=").append(username);
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
}
