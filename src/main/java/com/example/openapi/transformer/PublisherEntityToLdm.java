package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Publisher;
import com.example.openapi.repository.PublisherEntity;

//TODO :: Implement this
@Transformer
public class PublisherEntityToLdm implements Transformable<PublisherEntity, Publisher> {

    public Publisher transform(PublisherEntity publisherEntity) {
        return null;
    }
}
