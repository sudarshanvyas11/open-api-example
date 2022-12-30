package com.example.openapi.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class BookEntity {

    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AuthorEntity author;

    @Column
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PublisherEntity publisher;

    @Column
    private String genre;

    public BookEntity(final String title, final AuthorEntity author, final PublisherEntity publisher, final String genre) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.genre = genre;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public PublisherEntity getPublisher() {
        return publisher;
    }

    public String getGenre() {
        return genre;
    }
}
