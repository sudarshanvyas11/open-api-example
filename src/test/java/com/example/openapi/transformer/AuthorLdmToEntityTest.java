package com.example.openapi.transformer;

import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class AuthorLdmToEntityTest {
    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";

    @Nested
    class Preconditions {
        @Test
        void authorEntityMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new AuthorLdmToEntity().transform(null))
                    .withMessage("author must not be null");
        }
    }

    @Test
    void shouldTransformAuthorEntityToLdm() {
        final AuthorEntity entity = new AuthorEntity(FIRST_NAME, LAST_NAME, ImmutableList.of());
        final Author ldm = Author.builder()
                .withId(1L)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                //TODO :: There seems to be issue with auto code generation, skipping mapping of books for now
                //.withBooks()
                .build();

        assertThat(new AuthorLdmToEntity().transform(ldm))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(entity);
    }
}