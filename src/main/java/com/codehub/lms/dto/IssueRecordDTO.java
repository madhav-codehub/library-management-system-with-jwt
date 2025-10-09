package com.codehub.lms.dto;

import com.codehub.lms.entity.Book;
import com.codehub.lms.entity.User;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class IssueRecordDTO {
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private boolean isReturned;
    private User user;
    private Book book;
}
