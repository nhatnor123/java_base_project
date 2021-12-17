package com.example.demo.exception;

import com.example.demo.enums.ResponseStatus;

public class ApiException extends RuntimeException {

    private final ResponseStatus errorStatus;

    public ApiException(ResponseStatus errorStatus, String msg) {
        super(msg);
        this.errorStatus = errorStatus;
    }

    public ApiException(ResponseStatus errorStatus) {
        super(errorStatus.getMessage());
        this.errorStatus = errorStatus;
    }

    public ResponseStatus getErrorStatus() {
        return errorStatus;
    }
}
