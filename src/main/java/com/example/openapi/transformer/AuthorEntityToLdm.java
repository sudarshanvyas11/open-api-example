package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;
import com.google.common.collect.ImmutableList;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class AuthorEntityToLdm implements Transformable<AuthorEntity, Author> {
    public Author transform(AuthorEntity authorEntity) {
        notNull(authorEntity, "authorEntity must not be null");
        return Author.builder()
                .withId(authorEntity.getId())
                .withFirstName(authorEntity.getFirstName())
                .withLastName(authorEntity.getLastName())
                //TODO :: Implement mapping for books once issue with arrays is sorted
                .withBooks(ImmutableList.of())
                .build();
    }
}