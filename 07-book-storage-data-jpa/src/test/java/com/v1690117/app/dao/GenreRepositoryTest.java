package com.v1690117.app.dao;

import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GenreRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 10;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private GenreRepository dao;

    @DisplayName("Finds entity by id")
    @Test
    void findById() {
        Genre expected = new Genre(1L, "drama");
        assertThat(dao.findById(1L).get()).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Finds all entities")
    @Test
    void findAll() {
        Genre expected = new Genre(1L, "drama");
        assertThat(dao.findAll())
                .hasSize(EXPECTED_ENTITIES_NUMBER)
                .contains(expected);
    }

    @DisplayName("Adds new entity")
    @Test
    void insert() {
        Genre expected = new Genre("anything");
        Genre inserted = dao.save(expected);
        assertThat(manager.find(Genre.class, inserted.getId())
        ).extracting(Genre::getName).isEqualTo(expected.getName());
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        Genre first = dao.findById(1L).get();
        assertThat(first.getName()).isEqualTo("drama");
        dao.save(new Genre(first.getId(), "melodrama"));
        first = manager.find(Genre.class, 1L);
        assertThat(first.getName()).isEqualTo("melodrama");
    }

    @DisplayName("Deletes entity")
    @Test
    void delete() {
        Genre genre = manager.find(Genre.class, 10L);
        assertThat(genre)
                .isNotNull();
        dao.delete(genre);
        assertThat(manager.find(Genre.class, 10L))
                .isNull();
    }
}