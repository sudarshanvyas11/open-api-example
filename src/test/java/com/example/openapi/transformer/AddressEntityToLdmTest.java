package com.example.openapi.transformer;

import com.example.openapi.model.Address;
import com.example.openapi.repository.AddressEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class AddressEntityToLdmTest {

    private static final long ID = 1L;
    private static final String FIRST_LINE = "First";
    private static final String SECOND_LINE = "Second";
    private static final String POST_CODE = "ABCDEF";
    private static final String CITY = "City";
    private static final String COUNTRY = "Country";

    @Nested
    class Preconditions {
        @Test
        void addressEntityMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new AddressEntityToLdm().transform(null))
                    .withMessage("addressEntity must not be null");
        }
    }

    @Test
    void shouldTransformAddressEntityToLdm(@Mock final AddressEntity entity) {
        given(entity.getId()).willReturn(ID);
        given(entity.getFirstLine()).willReturn(FIRST_LINE);
        given(entity.getSecondLine()).willReturn(SECOND_LINE);
        given(entity.getPostCode()).willReturn(POST_CODE);
        given(entity.getCity()).willReturn(CITY);
        given(entity.getCountry()).willReturn(COUNTRY);

        final Address ldm = Address.builder()
                .withId(ID)
                .withFirstLine(FIRST_LINE)
                .withSecondLine(SECOND_LINE)
                .withPostCode(POST_CODE)
                .withCity(CITY)
                .withCountry(COUNTRY).build();

        assertThat(new AddressEntityToLdm().transform(entity))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(ldm);
    }
}