package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Config {

    private String id;

    private String key;

    private String value;

    private Boolean isDeleted;

    private String createdBy;

    private ZonedDateTime createdAt;

    private String modifiedBy;

    private ZonedDateTime modifiedAt;
}
