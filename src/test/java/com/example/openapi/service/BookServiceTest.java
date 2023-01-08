package com.example.openapi.service;

import com.example.openapi.model.Book;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.BookRepository;
import com.example.openapi.transformer.BookEntityToLdm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@MockitoSettings
class BookServiceTest {
    private static final long ID = 1L;
    private static final String BOOK_NAME = "Book Name";

    @Mock
    private BookEntityToLdm bookEntityToLdm;
    @Mock
    private BookRepository bookRepository;

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService(bookRepository, bookEntityToLdm);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void bookRepositoryMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookService(null, bookEntityToLdm))
                    .withMessage("bookRepository must not be null");
        }

        @Test
        void bookEntityToLdmMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookService(bookRepository, null))
                    .withMessage("bookEntityToLdm must not be null");
        }
    }

    @Nested
    class FindById {
        @Test
        void returnsOptionalEmptyWhenNoBookFoundForId() {
            given(bookRepository.findById(ID)).willReturn(Optional.empty());

            assertThat(bookService.findById(ID)).isEmpty();

            then(bookEntityToLdm).shouldHaveNoInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }

        @Test
        void returnsOptionalBookWhenFoundForAnId(@Mock final BookEntity bookEntity,
                                                 @Mock final Book book) {
            given(bookRepository.findById(ID)).willReturn(Optional.of(bookEntity));
            given(bookEntityToLdm.transform(bookEntity)).willReturn(book);

            assertThat(bookService.findById(ID)).contains(book);

            then(bookEntityToLdm).shouldHaveNoMoreInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }
    }

    @Nested
    class FindByTitle {

        @ParameterizedTest
        @EmptySource
        void titleMustNotBeBlank(final String title) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> bookService.findByTitle(title))
                    .withMessage("title must not be blank");
        }

        @Test
        void titleMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> bookService.findByTitle(null))
                    .withMessage("title must not be blank");
        }

        @Test
        void returnsOptionalEmptyWhenNoBookFoundForName() {
            given(bookRepository.findByTitle(BOOK_NAME)).willReturn(Optional.empty());

            assertThat(bookService.findByTitle(BOOK_NAME)).isEmpty();

            then(bookEntityToLdm).shouldHaveNoInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }

        @Test
        void returnsOptionalBookWhenFoundForAnId(@Mock final BookEntity bookEntity,
                                                 @Mock final Book book) {
            given(bookRepository.findByTitle(BOOK_NAME)).willReturn(Optional.of(bookEntity));
            given(bookEntityToLdm.transform(bookEntity)).willReturn(book);

            assertThat(bookService.findByTitle(BOOK_NAME)).contains(book);

            then(bookEntityToLdm).shouldHaveNoMoreInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }
    }
}