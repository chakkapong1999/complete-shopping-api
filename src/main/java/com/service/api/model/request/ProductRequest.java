/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.service.api.model.request;

/**
 *
 * @author Chakkapong
 */
public class ProductRequest {
    private String name;
    private Integer price;
    private String image;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ProductRequest{");
        sb.append("name=").append(name);
        sb.append(", price=").append(price);
        sb.append(", image=").append(image);
        sb.append('}');
        return sb.toString();
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
