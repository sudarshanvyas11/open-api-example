package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Address;
import com.example.openapi.repository.AddressEntity;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class AddressEntityToLdm implements Transformable<AddressEntity, Address> {

    public Address transform(final AddressEntity addressEntity) {
        notNull(addressEntity, "addressEntity must not be null");
        return Address.builder()
                .withId(addressEntity.getId())
                .withFirstLine(addressEntity.getFirstLine())
                .withSecondLine(addressEntity.getSecondLine())
                .withPostCode(addressEntity.getPostCode())
                .withCity(addressEntity.getCity())
                .withCountry(addressEntity.getCountry())
                .build();
    }
}
