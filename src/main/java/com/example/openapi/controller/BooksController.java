package com.example.openapi.controller;

import com.example.openapi.api.BooksApi;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.service.BooksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@RestController
public class BooksController implements BooksApi {
    private final BooksService booksService;

    public BooksController(final BooksService booksService) {
        this.booksService = notNull(booksService, "booksService must not be null");
    }

    @Override
    public ResponseEntity<List<Book>> listBooks() {
        final List<Book> books = booksService.findAll();
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByAuthor(final Author author) {
        notNull(author, "author must not be null");
        final List<Book> books = booksService.findByAuthor(author);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByGenre(final String genre) {
        notBlank(genre, "genre must not be blank");
        final List<Book> books = booksService.findByGenre(genre);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }
}
