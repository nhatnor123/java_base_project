package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private String id;
    private String coUserId;
    private String coMerchantId;
    private String name;
    private String identityNumber;
    private String placeOfIssue;
    private ZonedDateTime dateOfIssue;
    private String email;
    private String phoneNo;
    private String photoUrl;
    private Boolean isVerified;
    private String coStatus;
    private String soStatus;
    private Integer show;
    private String createdBy;
    private ZonedDateTime createdAt;
    private String modifiedBy;
    private ZonedDateTime modifiedAt;
}
