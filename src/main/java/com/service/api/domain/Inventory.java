/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.domain;

/**
 *
 * @author Chakkapong
 */
public class Inventory extends BaseDomain{
    private Integer productId;
    private Integer quantity;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Inventory{");
        sb.append("productId=").append(productId);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
