package com.example.openapi.controller;

import com.example.openapi.api.BooksApi;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

public class BooksController implements BooksApi {
    private final BookRepository bookRepository;

    public BooksController(final BookRepository bookRepository) {
        this.bookRepository = notNull(bookRepository, "bookRepository must not be null");
    }

    @Override
    public ResponseEntity<List<Book>> listBooks() {
        final List<Book> books = bookRepository.findAll();
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByAuthor(final Author author) {
        notNull(author, "author must not be null");
        final List<Book> books = bookRepository.findByAuthor(author);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByGenre(final String genre) {
        notBlank(genre, "genre must not be blank");
        final List<Book> books = bookRepository.findByGenre(genre);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(books);
    }
}
