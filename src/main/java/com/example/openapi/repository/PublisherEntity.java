package com.example.openapi.repository;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "publisher")
public class PublisherEntity {

    @Id
    @Column(nullable = false, insertable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private AddressEntity address;

    @Column
    private String website;

    @OneToMany(mappedBy = "publisher")
    private List<BookEntity> books;

    public PublisherEntity(final String name, final String email, final AddressEntity address, final String website, final List<BookEntity> books) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.website = website;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public AddressEntity getAddress() {
        return address;
    }

    public String getWebsite() {
        return website;
    }

    public List<BookEntity> getBooks() {
        return books;
    }
}
