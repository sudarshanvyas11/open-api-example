package com.example.openapi.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@JsonDeserialize(builder = Address.Builder.class)
public class Address {
    private final long id;

    @NotBlank
    @Size(min = 1, max = 100)
    private final String firstLine;
    private final String secondLine;

    @NotBlank
    @Size(min = 1, max = 20)
    private final String postCode;

    @NotBlank
    @Size(min = 1, max = 50)
    private final String city;
    private final String country;

    private Address(final Builder builder) {
        this.id = builder.id;
        this.firstLine = builder.firstLine;
        this.secondLine = builder.secondLine;
        this.postCode = builder.postCode;
        this.city = builder.city;
        this.country = builder.country;
    }

    public long getId() {
        return id;
    }

    public String getFirstLine() {
        return firstLine;
    }

    public String getSecondLine() {
        return secondLine;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private long id;
        private String firstLine;
        private String secondLine;
        private String postCode;
        private String city;
        private String country;

        private Builder() {
        }

        public Builder withId(final long id) {
            this.id = id;
            return this;
        }

        public Builder withFirstLine(final String firstLine) {
            this.firstLine = firstLine;
            return this;
        }

        public Builder withSecondLine(final String secondLine) {
            this.secondLine = secondLine;
            return this;
        }

        public Builder withPostCode(final String postCode) {
            this.postCode = postCode;
            return this;
        }

        public Builder withCity(final String city) {
            this.city = city;
            return this;
        }

        public Builder withCountry(final String country) {
            this.country = country;
            return this;
        }

        public Address build() {
            return new Address(this);
        }
    }
}
