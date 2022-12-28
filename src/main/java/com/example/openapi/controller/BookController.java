package com.example.openapi.controller;

import com.example.openapi.api.BookApi;
import com.example.openapi.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController implements BookApi {
    @Override
    public ResponseEntity<Book> getBookById(final Long id) {
        return null;
    }

    @Override
    public ResponseEntity<Book> getBookByName(final String name) {
        return null;
    }
}
