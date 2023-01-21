package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Book;
import com.example.openapi.model.Genre;
import com.example.openapi.repository.BookEntity;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class BookEntityToLdm implements Transformable<BookEntity, Book> {

    private final AuthorEntityToLdm authorEntityToLdm;
    private final PublisherEntityToLdm publisherEntityToLdm;

    public BookEntityToLdm(final AuthorEntityToLdm authorEntityToLdm,
                           final PublisherEntityToLdm publisherEntityToLdm) {

        this.authorEntityToLdm = notNull(authorEntityToLdm, "authorEntityToLdm must not be null");
        this.publisherEntityToLdm = notNull(publisherEntityToLdm, "publisherEntityToLdm must not be null");
    }

    public Book transform(final BookEntity bookEntity) {
        notNull(bookEntity, "bookEntity must not be null");

        return Book.builder()
                .withId(bookEntity.getId())
                .withTitle(bookEntity.getTitle())
                .withAuthor(authorEntityToLdm.transform(bookEntity.getAuthor()))
                .withPublisher(publisherEntityToLdm.transform(bookEntity.getPublisher()))
                .withGenre(Genre.valueOf(bookEntity.getGenre()))
                .build();
    }
}
