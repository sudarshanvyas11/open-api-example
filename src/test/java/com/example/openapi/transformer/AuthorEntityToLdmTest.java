package com.example.openapi.transformer;

import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class AuthorEntityToLdmTest {

    private static final long ID = 1L;
    private static final String FIRST_NAME = "First Name";
    private static final String LAST_NAME = "Last Name";

    @Nested
    class Preconditions {
        @Test
        void authorEntityMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new AuthorEntityToLdm().transform(null))
                    .withMessage("authorEntity must not be null");
        }
    }

    @Test
    void shouldTransformAuthorEntityToLdm(@Mock final AuthorEntity authorEntity) {
        given(authorEntity.getId()).willReturn(ID);
        given(authorEntity.getFirstName()).willReturn(FIRST_NAME);
        given(authorEntity.getLastName()).willReturn(LAST_NAME);

        final Author ldm = Author.builder()
                .withId(ID)
                .withFirstName(FIRST_NAME)
                .withLastName(LAST_NAME)
                //TODO :: There seems to be issue with auto code generation, skipping mapping of books for now
                .withBooks(ImmutableList.of())
                .build();

        assertThat(new AuthorEntityToLdm().transform(authorEntity))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(ldm);
    }
}