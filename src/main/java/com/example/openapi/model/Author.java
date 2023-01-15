package com.example.openapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonDeserialize(builder = Author.Builder.class)
public class Author {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final List<Book> books;

    public Author(final Builder builder) {
        this.id = builder.id;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.books = builder.books;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private long id;

        @NotBlank
        @Size(min = 1, max = 100)
        private String firstName;

        @NotBlank
        @Size(min = 1, max = 100)
        private String lastName;
        private List<Book> books;

        private Builder() {
        }

        public Builder withId(final long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstName(final String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(final String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withBooks(final List<Book> books) {
            this.books = books;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }
}
