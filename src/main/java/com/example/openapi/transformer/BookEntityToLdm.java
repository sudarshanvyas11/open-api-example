package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookEntity;

import static org.apache.commons.lang3.Validate.notNull;

//TODO :: Implement this
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
        final Book book = new Book();
        book.setId(bookEntity.getId());
        book.setTitle(bookEntity.getTitle());
        book.setAuthor(authorEntityToLdm.transform(bookEntity.getAuthor()));
        book.setPublisher(publisherEntityToLdm.transform(bookEntity.getPublisher()));
        book.setGenre(Book.GenreEnum.fromValue(bookEntity.getGenre()));
        return book;
    }
}
