package com.example.openapi.controller;

import com.example.openapi.model.Address;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import com.example.openapi.model.Publisher;
import com.example.openapi.service.BooksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.example.openapi.model.Genre.ACTION;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BooksControllerMvcTest {

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

    @MockBean
    private BooksService booksService;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    class ListBooks {
        @Test
        void returnsNotFoundStatusWhenNoBookFound() throws Exception {
            given(booksService.findAll()).willReturn(ImmutableList.of());
            mockMvc.perform(get("/books"))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturnBooksFound() throws Exception {
            final Book book = createBook();

            given(booksService.findAll()).willReturn(ImmutableList.of(book));
            final String response = mockMvc.perform(get("/books"))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            final Book[] actual = objectMapper.readValue(response, Book[].class);

            assertThat(Arrays.asList(actual))
                    .usingRecursiveFieldByFieldElementComparator()
                    .containsExactly(book);
        }
    }

    @Nested
    @Disabled("Unable to hit the url correctly, figure out the issue")
    class ListBooksByAuthor {
        @Test
        void returnsNotFoundStatusWhenNoBookFoundForAnAuthor() throws Exception {

            final String authorAsJson = objectMapper.writeValueAsString(createAuthor());

            given(booksService.findByAuthor(createAuthor())).willReturn(ImmutableList.of());
            mockMvc.perform(post("/books/author/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(authorAsJson)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturnBooksFoundForAnAuthor() throws Exception {
            final Book book = createBook();
            final String authorAsJson = objectMapper.writeValueAsString(createAuthor());

            given(booksService.findByAuthor(createAuthor())).willReturn(ImmutableList.of(book));
            final String response = mockMvc.perform(post("/books/author/")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(authorAsJson)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            final Book[] actual = objectMapper.readValue(response, Book[].class);

            assertThat(Arrays.asList(actual))
                    .usingRecursiveFieldByFieldElementComparator()
                    .containsExactly(book);
        }
    }

    @Nested
    class ListBooksByGenre {
        @Test
        void returnsNotFoundStatusWhenNoBookFoundForAGenre() throws Exception {

            given(booksService.findByGenre(ACTION.name())).willReturn(ImmutableList.of());
            mockMvc.perform(get("/books/genre/" + ACTION.name()))
                    .andExpect(status().isNotFound());
        }

        @Test
        void shouldReturnBooksFoundForAnAuthor() throws Exception {
            final Book book = createBook();
            given(booksService.findByGenre(ACTION.name())).willReturn(ImmutableList.of(book));

            final String response = mockMvc.perform(get("/books/genre/" + ACTION.name()))
                    .andExpect(status().isOk())
                    .andReturn()
                    .getResponse()
                    .getContentAsString();

            final Book[] actual = objectMapper.readValue(response, Book[].class);

            assertThat(Arrays.asList(actual))
                    .usingRecursiveFieldByFieldElementComparator()
                    .containsExactly(book);
        }
    }

    private static Author createAuthor() {
        return Author.builder()
                .withId(1L)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                .build();
    }

    private static Book createBook() {
        final Address address = Address.builder()
                .withId(1L)
                .withFirstLine(FIRST_LINE)
                .withSecondLine(SECOND_LINE)
                .withPostCode(POST_CODE)
                .withCity(CITY)
                .withCountry(COUNTRY)
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
                .withAuthor(createAuthor())
                .withPublisher(publisher)
                .withGenre(ACTION)
                .build();
    }
}