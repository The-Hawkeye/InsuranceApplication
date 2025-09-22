package com.mylearning.InsuranceApplication.exception;

public class ApiException extends RuntimeException {
    private final int status;

    public int getStatus() {
        return status;
    }

    public ApiException(String message, int status) {
        super(message);
        this.status = status;
    }
}

