package com.example.openapi;

import com.example.openapi.repository.AddressEntity;
import com.example.openapi.repository.AuthorEntity;
import com.example.openapi.repository.BookEntity;
import com.example.openapi.repository.BookRepository;
import com.example.openapi.repository.PublisherEntity;
import com.google.common.collect.ImmutableList;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BookStepdefs {

    private AddressEntity address;
    private AuthorEntity author;
    private PublisherEntity publisher;
    private ResultActions result;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MockMvc mockMvc;

    @Given("There are no books available")
    public void thereAreNoBooksAvailable() {
        // Placeholder - Do Nothing
    }

    @Given("An Address")
    public void anAddress(final DataTable dataTable) {
        final List<Map<String, String>> addressesMap = dataTable.asMaps();
        address = addressesMap.stream()
                .map(addressMap -> new AddressEntity(
                        addressMap.get("First Line"),
                        "",
                        addressMap.get("Post Code"),
                        addressMap.get("City"),
                        addressMap.get("Country")))
                .collect(ImmutableList.toImmutableList()).get(0);
    }

    @Given("An Author")
    public void anAuthor(final DataTable dataTable) {
        final List<Map<String, String>> authorsMap = dataTable.asMaps();
        author = authorsMap.stream()
                .map(authorMap -> new AuthorEntity(
                        authorMap.get("First Name"),
                        authorMap.get("Last Name"),
                        ImmutableList.of()))
                .collect(ImmutableList.toImmutableList()).get(0);
    }

    @Given("A Publisher")
    public void aPublisher(final DataTable dataTable) {
        final List<Map<String, String>> publishersMap = dataTable.asMaps();
        publisher = publishersMap.stream().map(
                        publisherMap -> new PublisherEntity(
                                publisherMap.get("Name"),
                                publisherMap.get("Email"),
                                address,
                                publisherMap.get("Website"),
                                ImmutableList.of()))
                .collect(ImmutableList.toImmutableList()).get(0);
    }

    @Given("There are books available")
    public void thereAreBooksAvailable(final DataTable dataTable) {
        final List<Map<String, String>> booksMap = dataTable.asMaps();
        final ImmutableList<BookEntity> books = booksMap.stream()
                .map(this::createBookFromMap)
                .collect(ImmutableList.toImmutableList());

        bookRepository.saveAllAndFlush(books);
    }

    private BookEntity createBookFromMap(final Map<String, String> bookMap) {
        return new BookEntity(
                bookMap.get("Title"),
                author,
                publisher,
                bookMap.get("Genre"));
    }


    @When("Book is requested for ID {int}")
    public void bookIsRequestedForID(int id) throws Exception {
        result = mockMvc.perform(get("/book/id/" + id));

    }

    @Then("Response is Not Found")
    public void responseIsNotFound() throws Exception {
        result.andExpect(status().isNotFound());
    }
}
