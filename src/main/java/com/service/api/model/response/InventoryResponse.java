package com.service.api.model.response;

/**
 * @author Chakkapong
 */
public class InventoryResponse extends BaseResponse{

    private boolean success;
    private Integer productId;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("InventoryResponse{");
        sb.append("success=").append(success);
        sb.append(", productId=").append(productId);
        sb.append('}');
        return sb.toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
