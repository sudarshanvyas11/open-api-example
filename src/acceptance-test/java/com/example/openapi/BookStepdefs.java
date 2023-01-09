package com.example.openapi;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class BookStepdefs {
    @Given("There are no books available")
    public void thereAreNoBooksAvailable() {

    }

    @When("Book is requested for ID {int}")
    public void bookIsRequestedForID(int id) {

    }

    @Then("Response is {string}")
    public void responseIsNotFound(final String response) {
    }

    @Given("There are books available")
    public void thereAreBooksAvailable(final DataTable dataTable) {
        final List<Map<String, String>> booksMap = dataTable.asMaps();
    }

    @And("An Address")
    public void anAddress(final DataTable dataTable) {
        final Map<String, String> addressMap = dataTable.asMap();
    }

    @Given("An Author")
    public void anAuthor(final DataTable dataTable) {
        final Map<String, String> authorMap = dataTable.asMap();
    }

    @And("A Publisher")
    public void aPublisher(final DataTable dataTable) {
        final Map<String, String> publisherMap = dataTable.asMap();
    }
}
