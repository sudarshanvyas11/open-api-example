package com.example.openapi.controller;

import com.example.openapi.model.Book;
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
            given(bookRepository.findById(1L)).willReturn(Optional.empty());
            assertThat(bookController.getBookById(1L)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnABookForAProvidedId(@Mock final Book book) {
            given(bookRepository.findById(1L)).willReturn(Optional.of(book));
            assertThat(bookController.getBookById(1L)).isEqualTo(ResponseEntity.ok(book));
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
            given(bookRepository.findByName("Book name")).willReturn(Optional.empty());
            assertThat(bookController.getBookByName("Book name")).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnABookForAProvidedName(@Mock final Book book) {
            given(bookRepository.findByName("Book name")).willReturn(Optional.of(book));
            assertThat(bookController.getBookByName("Book name")).isEqualTo(ResponseEntity.ok(book));
        }
    }
}