package com.example.openapi.service;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookRepository;
import com.example.openapi.transformer.BookEntityToLdm;
import com.google.common.collect.ImmutableList;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class BooksService {
    private final BookRepository bookRepository;
    private final BookEntityToLdm bookEntityToLdm;

    public BooksService(final BookRepository bookRepository, final BookEntityToLdm bookEntityToLdm) {
        this.bookRepository = notNull(bookRepository, "bookRepository must not be null");
        this.bookEntityToLdm = notNull(bookEntityToLdm, "bookEntityToLdm must not be null");
    }

    public List<Book> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntityToLdm::transform)
                .collect(ImmutableList.toImmutableList());
    }

    public List<Book> findByAuthor(final Author author) {
        return bookRepository.findByAuthor(notNull(author, "author must not be null"))
                .stream()
                .map(bookEntityToLdm::transform)
                .collect(ImmutableList.toImmutableList());
    }

    public List<Book> findByGenre(final String genre) {
        return bookRepository.findByGenre(notBlank(genre, "genre must not be blank"))
                .stream()
                .map(bookEntityToLdm::transform)
                .collect(ImmutableList.toImmutableList());
    }
}
