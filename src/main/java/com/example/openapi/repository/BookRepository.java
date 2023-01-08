package com.example.openapi.repository;

import com.example.openapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByTitle(String title);

    List<BookEntity> findByAuthor(AuthorEntity author);

    List<BookEntity> findByGenre(String genre);
}
