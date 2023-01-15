package com.example.openapi.transformer;

import com.example.openapi.model.Address;
import com.example.openapi.model.Publisher;
import com.example.openapi.repository.AddressEntity;
import com.example.openapi.repository.PublisherEntity;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoSettings;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;
import static org.mockito.BDDMockito.given;

@MockitoSettings
class PublisherEntityToLdmTest {

    private static final String PUBLISHER = "Publisher";
    private static final String EMAIL = "publisher@publisher.com";
    private static final String WEBSITE = "publisher.com";

    @Mock
    private AddressEntityToLdm addressEntityToLdm;
    private PublisherEntityToLdm publisherEntityToLdm;

    @BeforeEach
    void setUp() {
        publisherEntityToLdm = new PublisherEntityToLdm(addressEntityToLdm);
    }

    @Nested
    class ConstructorPreconditions {
        @Test
        void addressEntityToLdmMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> new PublisherEntityToLdm(null))
                    .withMessage("addressEntityToLdm must not be null");
        }

    }

    @Nested
    class Preconditions {
        @Test
        void publisherEntityMustNotBeNull() {
            assertThatNullPointerException()
                    .isThrownBy(() -> publisherEntityToLdm.transform(null))
                    .withMessage("publisherEntity must not be null");
        }

    }

    @Test
    void shouldTransformPublisherEntityToLdm(@Mock final AddressEntity addressEntity,
                                             @Mock final Address address) {
        given(addressEntityToLdm.transform(addressEntity)).willReturn(address);

        final PublisherEntity entity = new PublisherEntity(PUBLISHER, EMAIL, addressEntity, WEBSITE, ImmutableList.of());
        final Publisher ldm = new Publisher();
        ldm.setId(1L);
        ldm.setName(PUBLISHER);
        ldm.setEmail(EMAIL);
        ldm.setAddress(address);
        ldm.setWebsite(WEBSITE);
        assertThat(publisherEntityToLdm.transform(entity))
                .usingRecursiveComparison()
                .ignoringFields("id")
                .isEqualTo(ldm);
    }
}