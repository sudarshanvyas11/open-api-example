package com.example.openapi.transformer;

import com.example.openapi.component.Transformer;
import com.example.openapi.model.Author;
import com.example.openapi.repository.AuthorEntity;

//TODO :: Implement this
@Transformer
public class AuthorEntityToLdm implements Transformable<AuthorEntity, Author>{
    public Author transform(AuthorEntity authorEntity) {
        return null;
    }
}
