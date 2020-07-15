package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AuthorRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 24;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private AuthorRepository authorRepository;

    @DisplayName("Finds entity by id")
    @Test
    void findById() {
        Author expected = new Author(1L, "Martin", "Fowler");
        assertThat(authorRepository.findById(1L).get()).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Finds all entities")
    @Test
    void findAll() {
        Author mFowler = new Author(1L, "Martin", "Fowler");
        Author rMartin = new Author(10L, "Robert", "Martin");
        assertThat(authorRepository.findAll())
                .hasSize(EXPECTED_ENTITIES_NUMBER)
                .contains(mFowler)
                .contains(rMartin);
    }

    @DisplayName("Adds new entity")
    @Test
    void insert() {
        Author expected = new Author("Vladimir", "Veselov");
        Author inserted = authorRepository.save(expected);
        assertThat(manager.find(Author.class, inserted.getId())).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        long id = 24L;
        authorRepository.save(new Author(id, "Vladimir", "Veselov"));
        Author updated = manager.find(Author.class, id);
        assertThat(updated)
                .extracting(Author::getFirstName, Author::getLastName)
                .containsExactly("Vladimir", "Veselov");
    }

    @DisplayName("Deletes entity")
    @Test
    void delete() {
        long id = 24L;
        Author actual = manager.find(Author.class, id);
        assertThat(actual).isNotNull();
        authorRepository.delete(actual);
        assertThat(manager.find(Author.class, id)).isNull();
    }
}