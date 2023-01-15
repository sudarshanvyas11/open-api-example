package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;
import com.google.common.collect.ImmutableList;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class AuthorLdmToEntity implements Transformable<Author, AuthorEntity>{
    @Override
    public AuthorEntity transform(final Author author) {
        notNull(author, "author must not be null");
        return new AuthorEntity(author.getFirstName(), author.getLastName(), ImmutableList.of());
    }
}
