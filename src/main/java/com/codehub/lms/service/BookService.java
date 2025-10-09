package com.codehub.lms.service;

import com.codehub.lms.dto.BookDTO;
import com.codehub.lms.entity.Book;
import com.codehub.lms.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found!!")
        );
    }

    public Book addBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setIsbn(bookDTO.getIsbn());
        book.setQuantity(bookDTO.getQuantity());
        book.setAvailable(bookDTO.isAvailable());

        return bookRepository.save(book);
    }

    public Book updateBookById(Long id, BookDTO bookDTO) {
        Book oldBook = bookRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found!!")
        );
        oldBook.setTitle(bookDTO.getTitle());
        oldBook.setAuthor(bookDTO.getAuthor());
        oldBook.setIsbn(bookDTO.getIsbn());
        oldBook.setQuantity(bookDTO.getQuantity());
        oldBook.setAvailable(bookDTO.isAvailable());

        return bookRepository.save(oldBook);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
}
