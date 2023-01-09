package com.example.openapi;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

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
}
