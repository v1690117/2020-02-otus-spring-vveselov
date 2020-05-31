package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Genres DAO")
@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan("com.v1690117.app.dao.jdbc")
@ActiveProfiles("jdbc")
class GenreDaoTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 10;
    @Autowired
    private GenreDao dao;

    @DisplayName("Counts entities")
    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(EXPECTED_ENTITIES_NUMBER);
    }

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
        Genre expected = new Genre(11, "anything");
        dao.insert(expected);
        assertThat(dao.findById(expected.getId())).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        Genre first = dao.findById(1);
        assertThat(first.getName()).isEqualTo("drama");
        dao.update(new Genre(first.getId(), "melodrama"));
        first = dao.findById(1);
        assertThat(first.getName()).isEqualTo("melodrama");
    }

    @DisplayName("Can't delete entity because of relations")
    @Test
    void delete() {
        assertThatThrownBy(() -> {
            dao.delete(9);
        }).hasMessageContaining("constraint violation");
    }
}