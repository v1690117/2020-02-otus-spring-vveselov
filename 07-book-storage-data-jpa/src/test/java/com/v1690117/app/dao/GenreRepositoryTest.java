package com.v1690117.app.dao;

import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(GenreRepository.class)
class GenreRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 10;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private GenreDao dao;

    @DisplayName("Finds entity by id")
    @Test
    void findById() {
        Genre expected = new Genre(1, "drama");
        assertThat(dao.findById(1)).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Finds all entities")
    @Test
    void findAll() {
        Genre expected = new Genre(1, "drama");
        assertThat(dao.findAll())
                .hasSize(EXPECTED_ENTITIES_NUMBER)
                .contains(expected);
    }

    @DisplayName("Adds new entity")
    @Test
    void insert() {
        Genre expected = new Genre("anything");
        Genre inserted = dao.insert(expected);
        assertThat(manager.find(Genre.class, inserted.getId())
        ).extracting(Genre::getName).isEqualTo(expected.getName());
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        Genre first = dao.findById(1L);
        assertThat(first.getName()).isEqualTo("drama");
        dao.update(new Genre(first.getId(), "melodrama"));
        first = manager.find(Genre.class, 1L);
        assertThat(first.getName()).isEqualTo("melodrama");
    }

    @DisplayName("Deletes entity")
    @Test
    void delete() {
        assertThat(manager.find(Genre.class, 10L))
                .isNotNull();
        dao.delete(10);
        assertThat(manager.find(Genre.class, 10L))
                .isNull();
    }
}