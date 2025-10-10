package com.codehub.lms.controller;

import com.codehub.lms.entity.IssueRecord;
import com.codehub.lms.service.IssueRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/issuerecords")
@RequiredArgsConstructor
public class IssueRecordController {
    private final IssueRecordService issueRecordService;

    @PostMapping("/issuethebook/{bookId}")
    public ResponseEntity<IssueRecord> issueTheBook(@PathVariable Long bookId) {
        return ResponseEntity.ok(issueRecordService.issueTheBook(bookId));
    }

    @PostMapping("/returnthebook/{issueRecordId}")
    public ResponseEntity<IssueRecord> returnTheBook(@PathVariable Long issueRecordId) {
        return ResponseEntity.ok(issueRecordService.returnTheBook(issueRecordId));
    }
}
