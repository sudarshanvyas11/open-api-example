package com.example.openapi.service;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookRepository;
import com.example.openapi.transformer.AuthorLdmToEntity;
import com.example.openapi.transformer.BookEntityToLdm;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.commons.lang3.Validate.notBlank;
import static org.apache.commons.lang3.Validate.notNull;

@Service
public class BooksService {
    private final BookRepository bookRepository;
    private final BookEntityToLdm bookEntityToLdm;

    private final AuthorLdmToEntity authorLdmToEntity;

    public BooksService(final BookRepository bookRepository, final BookEntityToLdm bookEntityToLdm, final AuthorLdmToEntity authorLdmToEntity) {
        this.bookRepository = notNull(bookRepository, "bookRepository must not be null");
        this.bookEntityToLdm = notNull(bookEntityToLdm, "bookEntityToLdm must not be null");
        this.authorLdmToEntity = notNull(authorLdmToEntity, "authorLdmToEntity must not be null");
    }

    public List<Book> findAll() {
        return bookRepository.findAll()
                .stream()
                .map(bookEntityToLdm::transform)
                .collect(ImmutableList.toImmutableList());
    }

    public List<Book> findByAuthor(final Author author) {
        notNull(author, "author must not be null");
        return bookRepository.findByAuthor(authorLdmToEntity.transform(author))
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
