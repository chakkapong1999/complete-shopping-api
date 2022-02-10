/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.model.response;

/**
 *
 * @author Chakkapong
 */
public class UpdateProductResponse extends ProductResponse{
    private String message;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("UpdateProductResponse{");
        sb.append("message=").append(message);
        sb.append('}');
        return sb.toString();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
