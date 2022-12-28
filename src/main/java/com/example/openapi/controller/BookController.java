package com.example.openapi.controller;

import com.example.openapi.api.DefaultApi;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements DefaultApi {
    @Override
    public ResponseEntity<Book> getBookById(final Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Book> getBookByName(final String name) {
        return null;
    }

    @Override
    public ResponseEntity<List<Book>> listBooks() {
        return null;
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByAuthor(final Author author) {
        return null;
    }

    @Override
    public ResponseEntity<List<Book>> listBooksByGenre(final String genre) {
        return null;
    }
}
