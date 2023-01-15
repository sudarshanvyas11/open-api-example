package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Publisher;
import com.example.openapi.repository.PublisherEntity;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class PublisherEntityToLdm implements Transformable<PublisherEntity, Publisher> {

    private final AddressEntityToLdm addressEntityToLdm;

    public PublisherEntityToLdm(final AddressEntityToLdm addressEntityToLdm) {
        this.addressEntityToLdm = notNull(addressEntityToLdm, "addressEntityToLdm must not be null");
    }

    public Publisher transform(PublisherEntity publisherEntity) {
        notNull(publisherEntity, "publisherEntity must not be null");
        return Publisher.builder()
                .withId(publisherEntity.getId())
                .withName(publisherEntity.getName())
                .withEmail(publisherEntity.getEmail())
                .withWebsite(publisherEntity.getWebsite())
                .withAddress(addressEntityToLdm.transform(publisherEntity.getAddress()))
                .build();
    }
}
