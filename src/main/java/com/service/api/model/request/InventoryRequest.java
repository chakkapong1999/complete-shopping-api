package com.service.api.model.request;

/**
 * @author Chakkapong
 */
public class InventoryRequest {
    private Integer id;
    private Integer quantity;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryRequest{");
        sb.append("id=").append(id);
        sb.append(", quantity=").append(quantity);
        sb.append('}');
        return sb.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
