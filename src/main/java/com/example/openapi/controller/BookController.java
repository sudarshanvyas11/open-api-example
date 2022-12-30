package com.example.openapi.controller;

import com.example.openapi.api.BookApi;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@RestController
public class BookController implements BookApi {
    private final BookRepository bookRepository;

    public BookController(final BookRepository bookRepository) {
        this.bookRepository = notNull(bookRepository, "bookRepository must not be null");
    }

    @Override
    public ResponseEntity<Book> getBookById(final Long id) {
        notNull(id, "id must not be null");
        return bookRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Book> getBookByName(final String name) {
        notBlank(name, "name must not be blank");
        return bookRepository.findByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
