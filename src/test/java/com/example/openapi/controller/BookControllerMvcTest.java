package com.example.openapi.controller;

import com.example.openapi.model.Address;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.model.Genre;
import com.example.openapi.model.Publisher;
import com.example.openapi.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BookControllerMvcTest {

    private static final String TITLE = "Title";
    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";
    private static final String PUBLISHER = "Publisher";
    private static final String EMAIL = "publisher@publisher.com";
    private static final String WEBSITE = "publisher.com";
    private static final String FIRST_LINE = "First";
    private static final String SECOND_LINE = "Second";
    private static final String POST_CODE = "ABCDEF";
    private static final String CITY = "City";
    private static final String COUNTRY = "Country";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;

    @Nested
    class GetBookById {
        @Test
        void returnsNotFoundStatusWhenNoBookFoundById() throws Exception {
            given(bookService.findById(1L)).willReturn(Optional.empty());
            mockMvc.perform(get("/book/id/1"))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturnBookFoundById() throws Exception {
            final Book book = getBook();

            given(bookService.findById(1L)).willReturn(Optional.of(book));
            final String response = mockMvc.perform(get("/book/id/1"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            final Book actual = objectMapper.readValue(response, Book.class);

            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(book);
        }
    }

    @Nested
    class GetBookByTitle {
        @Test
        void returnsNotFoundStatusWhenNoBookFoundByTitle() throws Exception {
            given(bookService.findByTitle(TITLE)).willReturn(Optional.empty());
            mockMvc.perform(get("/book/title/" + TITLE))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturnBookFoundById() throws Exception {
            final Book book = getBook();

            given(bookService.findByTitle(TITLE)).willReturn(Optional.of(book));
            final String response = mockMvc.perform(get("/book/title/" + TITLE))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            final Book actual = objectMapper.readValue(response, Book.class);

            assertThat(actual)
                    .usingRecursiveComparison()
                    .isEqualTo(book);
        }
    }

    private static Book getBook() {
        final Address address = Address.builder()
                .withId(1L)
                .withFirstLine(FIRST_LINE)
                .withSecondLine(SECOND_LINE)
                .withPostCode(POST_CODE)
                .withCity(CITY)
                .withCountry(COUNTRY)
                .build();

        final Author author = Author.builder()
                .withId(1L)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();

        final Publisher publisher = Publisher.builder()
                .withId(1L)
                .withName(PUBLISHER)
                .withEmail(EMAIL)
                .withAddress(address)
                .withWebsite(WEBSITE)
                .build();

        return Book.builder()
                .withTitle(TITLE)
                .withAuthor(author)
                .withPublisher(publisher)
                .withGenre(Genre.ACTION)
                .build();
    }
}