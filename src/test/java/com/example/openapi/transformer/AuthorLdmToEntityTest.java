package com.example.openapi.transformer;

import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;
import com.example.openapi.repository.BookEntity;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

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
        final Author ldm = new Author();
        ldm.setId(1L);
        ldm.setFirstName(FIRST_NAME);
        ldm.setLastName(LAST_NAME);
        //TODO :: There seems to be issue with auto code generation, skipping mapping of books for now
        //ldm.setBooks(ImmutableList.of(book));
        assertThat(new AuthorLdmToEntity().transform(ldm))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(entity);
    }
}