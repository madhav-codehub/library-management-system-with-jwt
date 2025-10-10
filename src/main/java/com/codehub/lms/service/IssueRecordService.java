package com.codehub.lms.service;

import com.codehub.lms.entity.Book;
import com.codehub.lms.entity.IssueRecord;
import com.codehub.lms.entity.User;
import com.codehub.lms.repository.BookRepository;
import com.codehub.lms.repository.IssueRecordRepository;
import com.codehub.lms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class IssueRecordService {

    private final IssueRecordRepository issueRecordRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public IssueRecord issueTheBook(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(
                () -> new RuntimeException("Book not found!!")
        );
        if (book.getQuantity() <= 0 || !book.getIsAvailable()) {
            throw new RuntimeException("Book is not available!!");
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found!!")
        );
        IssueRecord issueRecord = new IssueRecord();
        issueRecord.setIssueDate(LocalDate.now());
        issueRecord.setDueDate(LocalDate.now().plusDays(14));
        issueRecord.setIsReturned(false);
        issueRecord.setUser(user);
        issueRecord.setBook(book);

        book.setQuantity(book.getQuantity() - 1);
        if (book.getQuantity() == 0) {
            book.setIsAvailable(false);
        }

        bookRepository.save(book);
        return issueRecordRepository.save(issueRecord);
    }

    public IssueRecord returnTheBook(Long issueRecordId) {
        IssueRecord issueRecord = issueRecordRepository.findById(issueRecordId)
                .orElseThrow(() -> new RuntimeException("IssueRecord not found!"));
        if (issueRecord.getIsReturned()) {
            throw new RuntimeException("Book already returned");
        }
        Book book = issueRecord.getBook();
        book.setQuantity(book.getQuantity() + 1);
        book.setIsAvailable(true);
        bookRepository.save(book);

        issueRecord.setIsReturned(true);
        issueRecord.setReturnDate(LocalDate.now());
        return issueRecordRepository.save(issueRecord);
    }
}
