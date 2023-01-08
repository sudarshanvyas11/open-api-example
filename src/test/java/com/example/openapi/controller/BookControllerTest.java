package com.example.openapi.controller;

import com.example.openapi.model.Book;
import com.example.openapi.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class BookControllerTest {
    private static final String TITLE = "Book title";
    private static final long ID = 1L;
    @Mock
    private BookService bookService;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController(bookService);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void bookRepositoryMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookController(null))
                    .withMessage("bookService must not be null");
        }
    }

    @Nested
    class GetBookById {

        @Test
        void idMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> bookController.getBookById(null))
                    .withMessage("id must not be null");
        }

        @Test
        void shouldReturnNotFoundStatusWhenNoBookAvailableById() {
            given(bookService.findById(ID)).willReturn(Optional.empty());
            assertThat(bookController.getBookById(ID)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnABookForAProvidedId(@Mock final Book book) {
            given(bookService.findById(ID)).willReturn(Optional.of(book));
            assertThat(bookController.getBookById(ID)).isEqualTo(ResponseEntity.ok(book));
        }
    }

    @Nested
    class GetBookByName {
        @ParameterizedTest
        @EmptySource
        void titleMustNotBeBlank(final String title) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> bookController.getBookByTitle(title))
                    .withMessage("title must not be blank");
        }

        @Test
        void nameMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> bookController.getBookByTitle(null))
                    .withMessage("title must not be blank");
        }

        @Test
        void shouldReturnNotFoundStatusWhenNoBookAvailableByName() {
            given(bookService.findByTitle(TITLE)).willReturn(Optional.empty());
            assertThat(bookController.getBookByTitle(TITLE)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnABookForAProvidedName(@Mock final Book book) {
            given(bookService.findByTitle(TITLE)).willReturn(Optional.of(book));
            assertThat(bookController.getBookByTitle(TITLE)).isEqualTo(ResponseEntity.ok(book));
        }
    }
}