package com.example.openapi.repository;

import com.google.common.collect.ImmutableList;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Transactional
class BookRepositoryIT {

    @Autowired
    private BookRepository bookRepository;

    @Nested
    class FindByTitle {

        @Test
        void shouldReturnEmptyWhenNoBookFoundByTitle() {
            final AuthorEntity authorEntity = new AuthorEntity("First name", "Last Name", ImmutableList.of());
            final AddressEntity addressEntity = new AddressEntity("First Line", "Second Line", "ABCDEF", "London", "UK");
            final PublisherEntity publisherEntity = new PublisherEntity("Publisher", "publisher@publisher.com", addressEntity, "publisher.com", ImmutableList.of());
            final BookEntity bookWithTitle = new BookEntity("Title 1", authorEntity, publisherEntity, "HORROR");
            bookRepository.save(bookWithTitle);

            assertThat(bookRepository.findByTitle("Title 2")).isEmpty();
        }

        @Test
        void shouldFindBookByTitle() {
            final AuthorEntity authorEntity = new AuthorEntity("First name", "Last Name", ImmutableList.of());
            final AddressEntity addressEntity = new AddressEntity("First Line", "Second Line", "ABCDEF", "London", "UK");
            final PublisherEntity publisherEntity = new PublisherEntity("Publisher", "publisher@publisher.com", addressEntity, "publisher.com", ImmutableList.of());
            final BookEntity bookWithTitle = new BookEntity("Title 1", authorEntity, publisherEntity, "HORROR");
            bookRepository.save(bookWithTitle);

            assertThat(bookRepository.findByTitle("Title 1")).containsSame(bookWithTitle);
        }
    }

    @Nested
    class FindByAuthor {

        @Test
        @Disabled("Issue with saving transient author entity")
        void shouldReturnEmptyWhenNoBookFoundByAuthor() {
            final AuthorEntity authorEntity1 = new AuthorEntity("First name 1", "Last Name", ImmutableList.of());
            final AuthorEntity authorEntity2 = new AuthorEntity("First name 2", "Last Name", ImmutableList.of());
            final AddressEntity addressEntity = new AddressEntity("First Line", "Second Line", "ABCDEF", "London", "UK");
            final PublisherEntity publisherEntity = new PublisherEntity("Publisher", "publisher@publisher.com", addressEntity, "publisher.com", ImmutableList.of());

            final BookEntity bookWithAuthor1 = new BookEntity("Title 1", authorEntity1, publisherEntity, "HORROR");
            bookRepository.saveAndFlush(bookWithAuthor1);

            assertThat(bookRepository.findByAuthor(authorEntity2)).isEmpty();
        }

        @Test
        void shouldFindBookByAuthor() {
            final AuthorEntity authorEntity1 = new AuthorEntity("First name 1", "Last Name", ImmutableList.of());
            final AddressEntity addressEntity = new AddressEntity("First Line", "Second Line", "ABCDEF", "London", "UK");
            final PublisherEntity publisherEntity = new PublisherEntity("Publisher", "publisher@publisher.com", addressEntity, "publisher.com", ImmutableList.of());
            final BookEntity bookWithAuthor1 = new BookEntity("Title 1", authorEntity1, publisherEntity, "HORROR");
            bookRepository.saveAndFlush(bookWithAuthor1);

            assertThat(bookRepository.findByAuthor(authorEntity1)).containsExactly(bookWithAuthor1);
        }
    }

    @Nested
    class FindByGenre {

        @Test
        void shouldReturnEmptyWhenNoBookFoundByGenre() {
            final AuthorEntity authorEntity = new AuthorEntity("First name", "Last Name", ImmutableList.of());
            final AddressEntity addressEntity = new AddressEntity("First Line", "Second Line", "ABCDEF", "London", "UK");
            final PublisherEntity publisherEntity = new PublisherEntity("Publisher", "publisher@publisher.com", addressEntity, "publisher.com", ImmutableList.of());
            final BookEntity bookWithTitle = new BookEntity("Title 1", authorEntity, publisherEntity, "HORROR");
            bookRepository.save(bookWithTitle);

            assertThat(bookRepository.findByGenre("MYSTERY")).isEmpty();
        }

        @Test
        void shouldFindBookByGenre() {
            final AuthorEntity authorEntity = new AuthorEntity("First name", "Last Name", ImmutableList.of());
            final AddressEntity addressEntity = new AddressEntity("First Line", "Second Line", "ABCDEF", "London", "UK");
            final PublisherEntity publisherEntity = new PublisherEntity("Publisher", "publisher@publisher.com", addressEntity, "publisher.com", ImmutableList.of());
            final BookEntity bookWithTitle = new BookEntity("Title 1", authorEntity, publisherEntity, "HORROR");
            bookRepository.save(bookWithTitle);

            assertThat(bookRepository.findByGenre("HORROR")).containsExactly(bookWithTitle);
        }
    }
}