package com.library_management.dto;

import com.library_management.enums.ResponseStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ResponseObject<T> {
    private Boolean success;
    private String code;
    private String message;
    private T data;
    private MetaDTO meta;
    private Object errors;

    public ResponseObject() {
    }

    public ResponseObject(Boolean success, ResponseStatus status) {
        this.success = success;
        this.code = status.getCode();
        this.message = status.getMessage();
    }

    public ResponseObject(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public ResponseObject(Boolean success, String code, String message, T data) {
        this.success = success;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseObject(Boolean success, ResponseStatus status, T data) {
        this.success = success;
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
    }

    public ResponseObject(Boolean success, ResponseStatus status, T data, MetaDTO meta) {
        this.success = success;
        this.code = status.getCode();
        this.message = status.getMessage();
        this.data = data;
        this.meta = meta;
    }

}
