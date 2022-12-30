package com.example.openapi.controller;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.BookRepository;
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
    private BookRepository bookRepository;
    private BooksController booksController;

    @BeforeEach
    void setUp() {
        booksController = new BooksController(bookRepository);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void bookRepositoryMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BooksController(null))
                    .withMessage("bookRepository must not be null");
        }
    }

    @Nested
    class ListBooks {
        @Test
        void shouldReturnNotFoundResponseWhenNoBooksAvailable() {
            given(bookRepository.findAll()).willReturn(ImmutableList.of());
            assertThat(booksController.listBooks()).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnListOfBooksWhenAvailable(@Mock final BookEntity bookEntity1,
                                                  @Mock final BookEntity bookEntity2,
                                                  @Mock final Book book1,
                                                  @Mock final Book book2) {
            final ImmutableList<BookEntity> bookEntities = ImmutableList.of(bookEntity1, bookEntity2);
            final ImmutableList<Book> books = ImmutableList.of(book1, book2);
            given(bookRepository.findAll()).willReturn(bookEntities);
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
            given(bookRepository.findByAuthor(author)).willReturn(ImmutableList.of());
            assertThat(booksController.listBooksByAuthor(author)).isEqualTo(ResponseEntity.notFound().build());
        }

        @Test
        void shouldReturnListOfBooksWhenAvailableForAnAuthor(@Mock final BookEntity bookEntity1,
                                                             @Mock final BookEntity bookEntity2,
                                                             @Mock final Book book1,
                                                             @Mock final Book book2) {
            final ImmutableList<BookEntity> bookEntities = ImmutableList.of(bookEntity1, bookEntity2);
            final ImmutableList<Book> books = ImmutableList.of(book1, book2);
            given(bookRepository.findByAuthor(author)).willReturn(bookEntities);
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
                    .isThrownBy(() ->  booksController.listBooksByGenre(null))
                    .withMessage("genre must not be blank");
        }

        @Test
        void shouldReturnNotFoundResponseWhenNoBooksAvailableForAGenre() {
            given(bookRepository.findByGenre(A_GENRE)).willReturn(ImmutableList.of());
            assertThat(booksController.listBooksByGenre(A_GENRE)).isEqualTo(ResponseEntity.notFound().build());
        }

        @ParameterizedTest
        @ValueSource(strings = {"MYSTERY", "THRILLER", "HORROR", "FICTION", "COMEDY", "BIOGRAPHY", "ROMANCE", "ACTION", "FANTASY"})
        void shouldReturnListOfBooksWhenAvailableForAGenre(final String genre,
                                                           @Mock final BookEntity bookEntity1,
                                                           @Mock final BookEntity bookEntity2,
                                                           @Mock final Book book1,
                                                           @Mock final Book book2) {
            final ImmutableList<BookEntity> bookEntities = ImmutableList.of(bookEntity1, bookEntity2);
            final ImmutableList<Book> books = ImmutableList.of(book1, book2);
            given(bookRepository.findByGenre(genre)).willReturn(bookEntities);
            assertThat(booksController.listBooksByGenre(genre)).isEqualTo(ResponseEntity.ok(books));
        }
    }
}