package com.example.openapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonDeserialize(builder = Book.Builder.class)
public class Book {

    private final long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private final String title;
    private final Author author;
    private final Publisher publisher;

    @NotBlank
    @Size(min = 1, max = 20)
    private final Genre genre;

    private Book(final Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.genre = builder.genre;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public Author getAuthor() {
        return author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public Genre getGenre() {
        return genre;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private long id;
        private String title;
        private Author author;
        private Publisher publisher;
        private Genre genre;

        private Builder() {
        }

        public Builder withId(final long id) {
            this.id = id;
            return this;
        }

        public Builder withTitle(final String title) {
            this.title = title;
            return this;
        }

        public Builder withAuthor(final Author author) {
            this.author = author;
            return this;
        }

        public Builder withPublisher(final Publisher publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder withGenre(final Genre genre) {
            this.genre = genre;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
