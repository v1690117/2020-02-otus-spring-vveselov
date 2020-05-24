package com.v1690117.app.dao;

import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan("com.v1690117.app.dao")
class GenreDaoTest {
    @Autowired
    private GenreDao dao;

    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(9);
    }

    @Test
    void findById() {
        Genre expected = new Genre(1, "drama");
        assertThat(dao.findById(1)).isEqualToComparingFieldByField(expected);
    }

    @Test
    void findAll() {
        Genre expected = new Genre(1, "drama");
        assertThat(dao.findAll())
                .hasSize(9)
                .contains(expected);
    }

    @Test
    void insert() {
        Genre expected = new Genre(10, "anything");
        dao.insert(expected);
        assertThat(dao.findById(expected.getId())).isEqualToComparingFieldByField(expected);
    }

    @Test
    void update() {
        Genre first = dao.findById(1);
        assertThat(first.getName()).isEqualTo("drama");
        dao.update(new Genre(first.getId(), "melodrama"));
        assertThat(first.getName()).isEqualTo("melodrama");

    }

    @Test
    void delete() {
        assertThatThrownBy(() -> {
            dao.delete(9);
        }).hasMessageContaining("constraint violation");
    }
}