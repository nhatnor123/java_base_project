package com.example.demo.dto.request.config;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class GetByKeyReq {
    @NotBlank
    private String key;
}
