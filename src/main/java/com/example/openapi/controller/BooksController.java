package com.example.openapi.controller;

import com.example.openapi.api.BooksApi;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

//TODO :: Change this class to use a BookService instead
@RestController
public class BooksController implements BooksApi {
    private final BookRepository bookRepository;

    public BooksController(final BookRepository bookRepository) {
        this.bookRepository = notNull(bookRepository, "bookRepository must not be null");
    }

    @Override
    public ResponseEntity<List<Book>> listBooks() {
        final List<BookEntity> books = bookRepository.findAll();
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(getTransformedBooks(books));
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByAuthor(final Author author) {
        notNull(author, "author must not be null");
        final List<BookEntity> books = bookRepository.findByAuthor(author);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(getTransformedBooks(books));
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByGenre(final String genre) {
        notBlank(genre, "genre must not be blank");
        final List<BookEntity> books = bookRepository.findByGenre(genre);
        return books.isEmpty()
                ? ResponseEntity.notFound().build()
                : ResponseEntity.ok(getTransformedBooks(books));
    }

    private static List<Book> getTransformedBooks(final List<BookEntity> books) {
        return books.stream()
                .map(BooksController::transform)
                .collect(Collectors.toList());
    }

    private static Book transform(final BookEntity bookEntity) {
        return null;
    }
}
