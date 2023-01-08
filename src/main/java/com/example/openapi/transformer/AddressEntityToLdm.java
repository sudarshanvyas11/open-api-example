package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Address;
import com.example.openapi.repository.AddressEntity;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class AddressEntityToLdm implements Transformable<AddressEntity, Address> {

    public Address transform(final AddressEntity addressEntity) {
        notNull(addressEntity, "addressEntity must not be null");
        final Address address = new Address();
        address.setId(addressEntity.getId());
        address.setFirstLine(addressEntity.getFirstLine());
        address.setSecondLine(addressEntity.getSecondLine());
        address.setPostCode(addressEntity.getPostCode());
        address.setCity(addressEntity.getCity());
        address.setCountry(addressEntity.getCountry());
        return address;
    }
}
