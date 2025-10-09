package com.codehub.lms.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookDTO {
    private String title;
    private String author;
    private String isbn;
    private Integer quantity;
    private boolean isAvailable;
}
