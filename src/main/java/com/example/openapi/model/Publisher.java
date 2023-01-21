package com.example.openapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.List;

@JsonDeserialize(builder = Publisher.Builder.class)
public class Publisher {
    private final long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private final String name;

    @NotBlank
    @Size(min = 1, max = 100)
    private final String email;

    @NotBlank
    @Size(min = 1, max = 100)
    private final String website;
    private final Address address;
    private final List<Book> books;

    private Publisher(final Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.email = builder.email;
        this.website = builder.website;
        this.address = builder.address;
        this.books = builder.books;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getWebsite() {
        return website;
    }

    public Address getAddress() {
        return address;
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
        private String name;
        private String email;
        private String website;
        private Address address;
        private List<Book> books;

        private Builder() {
        }

        public Builder withId(final long id) {
            this.id = id;
            return this;
        }

        public Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Builder withEmail(final String email) {
            this.email = email;
            return this;
        }

        public Builder withWebsite(final String website) {
            this.website = website;
            return this;
        }

        public Builder withAddress(final Address address) {
            this.address = address;
            return this;
        }

        public Builder withBooks(final List<Book> books) {
            this.books = books;
            return this;
        }

        public Publisher build() {
            return new Publisher(this);
        }
    }
}
