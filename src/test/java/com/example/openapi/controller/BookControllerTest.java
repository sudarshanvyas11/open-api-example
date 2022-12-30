package com.example.openapi.controller;

import com.example.openapi.model.Book;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.BookRepository;
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
    private static final String NAME = "Book name";
    private static final long ID = 1L;
    @Mock
    private BookRepository bookRepository;
    private BookController bookController;

    @BeforeEach
    void setUp() {
        bookController = new BookController(bookRepository);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void bookRepositoryMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BookController(null))
                    .withMessage("bookRepository must not be null");
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
            given(bookRepository.findById(ID)).willReturn(Optional.empty());
            assertThat(bookController.getBookById(ID)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnABookForAProvidedId(@Mock final BookEntity bookEntity,
                                             @Mock final Book book) {
            given(bookRepository.findById(ID)).willReturn(Optional.of(bookEntity));
            assertThat(bookController.getBookById(ID)).isEqualTo(ResponseEntity.ok(book));
        }
    }

    @Nested
    class GetBookByName {
        @ParameterizedTest
        @EmptySource
        void nameMustNotBeBlank(final String name) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> bookController.getBookByName(name))
                    .withMessage("name must not be blank");
        }

        @Test
        void nameMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> bookController.getBookByName(null))
                    .withMessage("name must not be blank");
        }

        @Test
        void shouldReturnNotFoundStatusWhenNoBookAvailableByName() {
            given(bookRepository.findByName(NAME)).willReturn(Optional.empty());
            assertThat(bookController.getBookByName(NAME)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnABookForAProvidedName(@Mock final BookEntity bookEntity,
                                               @Mock final Book book) {
            given(bookRepository.findByName(NAME)).willReturn(Optional.of(bookEntity));
            assertThat(bookController.getBookByName(NAME)).isEqualTo(ResponseEntity.ok(book));
        }
    }
}