/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.model.request;

/**
 *
 * @author Chakkapong
 */
public class ChangePasswordRequest extends LoginRequest {

    private String newPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ChangePasswordRequest{");
        sb.append("newPassword=").append(newPassword);
        sb.append('}');
        return sb.toString();
    }

}
