package com.service.api.model.response;

/**
 * @author Chakkapong
 */
public class ErrorResponse extends BaseResponse{
    private String status;
    private String timestamp;
    private String statusCode;
    private String error;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ErrorResponse{");
        sb.append("status=").append(status);
        sb.append(", timestamp='").append(timestamp).append('\'');
        sb.append(", statusCode='").append(statusCode).append('\'');
        sb.append(", error='").append(error).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public String isStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
