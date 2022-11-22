package com.hunter2.deserialization.model;

public class ApiResponse {
    private boolean success = true;
    private String payload;

    public ApiResponse(boolean success) {
        this.success = success;
    }

    public ApiResponse(){}

    public ApiResponse(String s) {
        this.payload = s;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }
}