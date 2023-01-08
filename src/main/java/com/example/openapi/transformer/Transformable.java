package com.example.openapi.transformer;

public interface Transformable<FROM, TO> {
    TO transform(FROM from);
}
