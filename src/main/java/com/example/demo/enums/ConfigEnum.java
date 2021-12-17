package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ConfigEnum {
    API_GATEWAY_URL("API_GATEWAY_URL", "");

    private final String code;
    private final String value;

    ConfigEnum(String code, String value) {
        this.code = code;
        this.value = value;
    }
}
