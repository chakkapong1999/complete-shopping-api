package com.service.api.domain.vo;

/**
 * @author Chakkapong
 */
public class InventoryVO {

    private Integer productId;
    private Integer quantity;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryVO{");
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
