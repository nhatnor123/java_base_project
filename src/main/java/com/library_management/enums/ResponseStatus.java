package com.library_management.enums;

public enum ResponseStatus {

    SUCCESSFUL("SUCCESSFUL", "Success"),
    UNHANDLED_ERROR("UNHANDLED_ERROR", "Unhandled error", "Unhandled error: %s"),
    REQUEST_BODY_INVALID("REQUEST_BODY_INVALID", "Request body is invalid", "Request body is invalid: %s"),
    FIELD_INVALID("FIELD_INVALID", "Field invalid", "Field invalid: %s"),
    CALL_HTTP_HAS_ERROR("CALL_HTTP_HAS_ERROR", "Call http has error", "Call http has error: %s"),

    USER_NOT_FOUND("USER_NOT_FOUND", "User not found", "User not found: %s"),
    ;

    private String message;
    private String messageFormat;
    private String code;

    ResponseStatus(String code, String message) {
        this.message = message;
        this.code = code;
    }

    ResponseStatus(String code, String message, String messageFormat) {
        this.message = message;
        this.messageFormat = messageFormat;
        this.code = code;
    }

    public ResponseStatus formatMessage(Object... str) {
        if (this.messageFormat != null) {
            this.message = String.format(this.messageFormat, str);
        }
        return this;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    public String getMessageFormat() {
        return messageFormat;
    }
}
