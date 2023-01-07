package com.example.openapi.controller;

import com.example.openapi.api.BookApi;
import com.example.openapi.model.Book;
import com.example.openapi.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@RestController
public class BookController implements BookApi {
    private final BookService bookService;

    public BookController(final BookService bookService) {
        this.bookService = notNull(bookService, "bookService must not be null");
    }

    @Override
    public ResponseEntity<Book> getBookById(final Long id) {
        notNull(id, "id must not be null");
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Book> getBookByName(final String name) {
        notBlank(name, "name must not be blank");
        return bookService.findByTitle(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
