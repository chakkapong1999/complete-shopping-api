package com.service.api.model.response;

/**
 * @author Chakkapong
 */
public class ErrorResponse extends BaseResponse{
    private String timestamp;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("timestamp='").append(timestamp).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
