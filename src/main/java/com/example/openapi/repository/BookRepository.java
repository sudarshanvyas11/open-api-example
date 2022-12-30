package com.example.openapi.repository;

import com.example.openapi.model.Author;
import com.example.openapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByName(String bookName);

    List<Book> findByAuthor(Author author);

    List<Book> findByGenre(String genre);
}
