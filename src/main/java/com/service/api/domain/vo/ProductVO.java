package com.service.api.domain.vo;

/**
 * @author Chakkapong
 */
public class ProductVO {
    private Integer productId;
    private String name;
    private Integer price;
    private String image;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductVO{");
        sb.append("productId=").append(productId);
        sb.append(", name='").append(name).append('\'');
        sb.append(", price=").append(price);
        sb.append(", image='").append(image).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
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
