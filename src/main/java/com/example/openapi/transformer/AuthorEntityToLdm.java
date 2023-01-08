package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;

import static org.apache.commons.lang3.Validate.notNull;

@Transformer
public class AuthorEntityToLdm implements Transformable<AuthorEntity, Author> {
    public Author transform(AuthorEntity authorEntity) {
        notNull(authorEntity, "authorEntity must not be null");
        final Author author = new Author();
        author.setId(authorEntity.getId());
        author.setFirstName(authorEntity.getFirstName());
        author.setLastName(authorEntity.getLastName());
        //TODO :: Implement mapping for books once issue with arrays is sorted
        return author;
    }
}
