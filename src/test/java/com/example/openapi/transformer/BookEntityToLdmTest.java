package com.example.openapi.transformer;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.model.Book.GenreEnum;
import com.example.openapi.model.Publisher;
import com.example.openapi.repository.AuthorEntity;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.PublisherEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class BookEntityToLdmTest {

    private static final String TITLE = "Title";
    @Mock
    private AuthorEntityToLdm authorEntityToLdm;

    @Mock
    private PublisherEntityToLdm publisherEntityToLdm;

    @Nested
    class ConstructorPreconditions {
        @Test
        void authorEntityToLdmMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookEntityToLdm(null, publisherEntityToLdm))
                    .withMessage("authorEntityToLdm must not be null");
        }

        @Test
        void publisherEntityToLdmMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookEntityToLdm(authorEntityToLdm, null))
                    .withMessage("publisherEntityToLdm must not be null");
        }
    }

    @Nested
    class Preconditions {
        @Test
        void bookEntityMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookEntityToLdm(authorEntityToLdm, publisherEntityToLdm).transform(null))
                    .withMessage("bookEntity must not be null");
        }
    }

    @ParameterizedTest
    @EnumSource(value = GenreEnum.class)
    void shouldTransformABookEntityToLdm(final GenreEnum genreEnum,
                                         @Mock final AuthorEntity authorEntity,
                                         @Mock final Author author,
                                         @Mock final PublisherEntity publisherEntity,
                                         @Mock final Publisher publisher) {
        given(authorEntityToLdm.transform(authorEntity)).willReturn(author);
        given(publisherEntityToLdm.transform(publisherEntity)).willReturn(publisher);
        final Book book = new Book();
        book.setTitle(TITLE);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(genreEnum);
        assertThat(new BookEntityToLdm(authorEntityToLdm, publisherEntityToLdm).transform(new BookEntity(TITLE, authorEntity, publisherEntity, genreEnum.getValue()))).isEqualTo(book);
    }
}