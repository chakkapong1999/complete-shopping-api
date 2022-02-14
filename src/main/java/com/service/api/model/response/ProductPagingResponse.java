package com.service.api.model.response;

import com.service.api.domain.vo.ProductVO;

import java.util.List;

/**
 * @author Chakkapong
 */
public class ProductPagingResponse {
    private int totalRow;
    private List<ProductVO> result;

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }





    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ProductPagingResponse{");
        sb.append("totalRow=").append(totalRow);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }



    public List<ProductVO> getResult() {
        return result;
    }

    public void setResult(List<ProductVO> result) {
        this.result = result;
    }
}
