package com.example.openapi.repository;

import com.example.openapi.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//TODO :: Make IT tests work
@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {
    Optional<BookEntity> findByName(String bookName);

    List<BookEntity> findByAuthor(Author author);

    List<BookEntity> findByGenre(String genre);
}
