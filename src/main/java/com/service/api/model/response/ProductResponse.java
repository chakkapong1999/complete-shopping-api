/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.model.response;

/**
 *
 * @author Chakkapong
 */
public class ProductResponse extends BaseResponse{
    
    private Boolean success;
    private String name;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductResponse{");
        sb.append("success=").append(success);
        sb.append(", name=").append(name);
        sb.append('}');
        return sb.toString();
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
