package com.example.openapi.transformer;

import com.example.openapi.model.Address;
import com.example.openapi.repository.AddressEntity;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

@MockitoSettings
class AddressEntityToLdmTest {

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
    void shouldTransformAddressEntityToLdm() {
        final AddressEntity entity = new AddressEntity(FIRST_LINE, SECOND_LINE, POST_CODE, CITY, COUNTRY);
        final Address ldm = new Address();
        ldm.setId(1L);
        ldm.setFirstLine(FIRST_LINE);
        ldm.setSecondLine(SECOND_LINE);
        ldm.setPostCode(POST_CODE);
        ldm.setCity(CITY);
        ldm.setCountry(COUNTRY);
        assertThat(new AddressEntityToLdm().transform(entity))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(ldm);
    }
}