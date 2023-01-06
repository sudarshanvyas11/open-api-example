package com.example.openapi.service;

import com.example.openapi.model.Book;
import com.example.openapi.repository.BookRepository;
import com.example.openapi.transformer.BookEntityToLdm;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final BookEntityToLdm bookEntityToLdm;

    public BookService(final BookRepository bookRepository, final BookEntityToLdm bookEntityToLdm) {
        this.bookRepository = notNull(bookRepository, "bookRepository must not be null");
        this.bookEntityToLdm = notNull(bookEntityToLdm, "bookEntityToLdm must not be null");
    }

    public Optional<Book> findById(final long id) {
        return bookRepository.findById(id)
                .map(bookEntityToLdm::transform);
    }

    public Optional<Book> findByName(final String name) {
        return bookRepository.findByTitle(notBlank(name, "name must not be blank"))
                .map(bookEntityToLdm::transform);
    }
}
