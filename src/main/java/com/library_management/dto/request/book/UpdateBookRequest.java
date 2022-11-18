package com.library_management.dto.request.book;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookRequest {
    private String title;
    private String author;
    private String description;
    private String type;
    private Long pageAmount;
    private Long dateRelease;
    private String imageUrl;
}
