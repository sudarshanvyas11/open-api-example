package com.example.openapi.controller;

import com.example.openapi.model.Address;
import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
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
        final Address address = new Address();
        address.setId(1L);
        address.setFirstLine(FIRST_LINE);
        address.setSecondLine(SECOND_LINE);
        address.setPostCode(POST_CODE);
        address.setCity(CITY);
        address.setCountry(COUNTRY);

        final Author author = new Author();
        author.setId(1L);
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);

        final Publisher publisher = new Publisher();
        publisher.setId(1L);
        publisher.setName(PUBLISHER);
        publisher.setEmail(EMAIL);
        publisher.setAddress(address);
        publisher.setWebsite(WEBSITE);

        final Book book = new Book();
        book.setTitle(TITLE);
        book.setAuthor(author);
        book.setPublisher(publisher);
        book.setGenre(Book.GenreEnum.ACTION);
        return book;
    }
}