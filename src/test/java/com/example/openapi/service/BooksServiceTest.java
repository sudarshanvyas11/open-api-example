package com.example.openapi.service;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.BookRepository;
import com.example.openapi.transformer.BookEntityToLdm;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@MockitoSettings
class BooksServiceTest {

    private static final String A_GENRE = "MYSTERY";
    @Mock
    private BookEntityToLdm bookEntityToLdm;
    @Mock
    private BookRepository bookRepository;

    private BooksService booksService;

    @BeforeEach
    void setUp() {
        booksService = new BooksService(bookRepository, bookEntityToLdm);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void bookRepositoryMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BooksService(null, bookEntityToLdm))
                    .withMessage("bookRepository must not be null");
        }

        @Test
        void bookEntityToLdmMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new BooksService(bookRepository, null))
                    .withMessage("bookEntityToLdm must not be null");
        }
    }

    @Nested
    class FindAll {

        @Test
        void returnsAnEmptyListWhenNoBooksFound() {
            given(bookRepository.findAll()).willReturn(ImmutableList.of());
            assertThat(booksService.findAll()).isEmpty();

            then(bookEntityToLdm).shouldHaveNoInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }

        @Test
        void shouldFindAllBooks(@Mock final BookEntity bookEntity,
                                @Mock final Book book) {
            given(bookRepository.findAll()).willReturn(ImmutableList.of(bookEntity));
            given(bookEntityToLdm.transform(bookEntity)).willReturn(book);

            assertThat(booksService.findAll()).containsExactly(book);

            then(bookEntityToLdm).shouldHaveNoMoreInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }
    }

    @Nested
    class FindByAuthor {

        @Test
        void authorMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> booksService.findByAuthor(null))
                    .withMessage("author must not be null");
        }

        @Test
        void returnsAnEmptyListWhenNoBooksFoundForAnAuthor(@Mock final Author author) {
            given(bookRepository.findByAuthor(author)).willReturn(ImmutableList.of());
            assertThat(booksService.findByAuthor(author)).isEmpty();

            then(bookEntityToLdm).shouldHaveNoInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }

        @Test
        void shouldFindAllBooksForAnAuthor(@Mock final Author author,
                                           @Mock final BookEntity bookEntity,
                                           @Mock final Book book) {
            given(bookRepository.findByAuthor(author)).willReturn(ImmutableList.of(bookEntity));
            given(bookEntityToLdm.transform(bookEntity)).willReturn(book);

            assertThat(booksService.findByAuthor(author)).containsExactly(book);

            then(bookEntityToLdm).shouldHaveNoMoreInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }
    }

    @Nested
    class FindByGenre {

        @ParameterizedTest
        @EmptySource
        void genreMustNotBeBlank(final String genre) {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> booksService.findByGenre(genre))
                    .withMessage("genre must not be blank");
        }

        @Test
        void genreMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> booksService.findByGenre(null))
                    .withMessage("genre must not be blank");
        }

        @Test
        void returnsAnEmptyListWhenNoBooksFoundForAGenre() {
            given(bookRepository.findByGenre(A_GENRE)).willReturn(ImmutableList.of());
            assertThat(booksService.findByGenre(A_GENRE)).isEmpty();

            then(bookEntityToLdm).shouldHaveNoInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }

        @Test
        void shouldFindAllBooksForAGenre(@Mock final BookEntity bookEntity,
                                         @Mock final Book book) {
            given(bookRepository.findByGenre(A_GENRE)).willReturn(ImmutableList.of(bookEntity));
            given(bookEntityToLdm.transform(bookEntity)).willReturn(book);

            assertThat(booksService.findByGenre(A_GENRE)).containsExactly(book);

            then(bookEntityToLdm).shouldHaveNoMoreInteractions();
            then(bookRepository).shouldHaveNoMoreInteractions();
        }
    }
}