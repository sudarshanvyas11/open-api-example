package com.example.openapi.controller;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.service.BooksService;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class BooksControllerTest {

    private static final String A_GENRE = "MYSTERY";
    @Mock
    private BooksService booksService;
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        booksController = new BooksController(booksService);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void bookRepositoryMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BooksController(null))
                    .withMessage("booksService must not be null");
        }
    }

    @Nested
    class ListBooks {
        @Test
        void shouldReturnNotFoundResponseWhenNoBooksAvailable() {
            given(booksService.findAll()).willReturn(ImmutableList.of());
            assertThat(booksController.listBooks()).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnListOfBooksWhenAvailable(@Mock final Book book1,
                                                  @Mock final Book book2) {
            final ImmutableList<Book> books = ImmutableList.of(book1, book2);
            given(booksService.findAll()).willReturn(books);
            assertThat(booksController.listBooks()).isEqualTo(ResponseEntity.ok(books));
        }
    }

    @Nested
    class ListBooksByAuthor {

        @Mock
        private Author author;

        @Test
        void authorMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> booksController.listBooksByAuthor(null))
                    .withMessage("author must not be null");
        }

        @Test
        void shouldReturnNotFoundResponseWhenNoBooksAvailableForAnAuthor() {
            given(booksService.findByAuthor(author)).willReturn(ImmutableList.of());
            assertThat(booksController.listBooksByAuthor(author)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnListOfBooksWhenAvailableForAnAuthor(@Mock final Book book1,
                                                             @Mock final Book book2) {
            final ImmutableList<Book> books = ImmutableList.of(book1, book2);
            given(booksService.findByAuthor(author)).willReturn(books);
            assertThat(booksController.listBooksByAuthor(author)).isEqualTo(ResponseEntity.ok(books));
        }
    }

    @Nested
    class ListBooksByGenre {

        @ParameterizedTest
        @EmptySource
        void genreMustNotBeBlank(final String genre) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> booksController.listBooksByGenre(genre))
                    .withMessage("genre must not be blank");
        }

        @Test
        void genreMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> booksController.listBooksByGenre(null))
                    .withMessage("genre must not be blank");
        }

        @Test
        void shouldReturnNotFoundResponseWhenNoBooksAvailableForAGenre() {
            given(booksService.findByGenre(A_GENRE)).willReturn(ImmutableList.of());
            assertThat(booksController.listBooksByGenre(A_GENRE)).isEqualTo(ResponseEntity.notFound().build());
        }

        @ParameterizedTest
        @ValueSource(strings = {"MYSTERY", "THRILLER", "HORROR", "FICTION", "COMEDY", "BIOGRAPHY", "ROMANCE", "ACTION", "FANTASY"})
        void shouldReturnListOfBooksWhenAvailableForAGenre(final String genre,
                                                           @Mock final Book book1,
                                                           @Mock final Book book2) {
            final ImmutableList<Book> books = ImmutableList.of(book1, book2);
            given(booksService.findByGenre(genre)).willReturn(books);
            assertThat(booksController.listBooksByGenre(genre)).isEqualTo(ResponseEntity.ok(books));
        }
    }
}