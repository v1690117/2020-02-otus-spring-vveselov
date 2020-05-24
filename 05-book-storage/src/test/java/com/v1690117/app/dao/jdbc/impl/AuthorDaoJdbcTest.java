package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Authors DAO")
@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan("com.v1690117.app.dao")
class AuthorDaoJdbcTest {
    @Autowired
    private AuthorDao dao;

    @DisplayName("Counts entities")
    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(23);
    }

    @DisplayName("Finds entity by id")
    @Test
    void findById() {
        Author expected = new Author(1, "Martin", "Fowler");
        assertThat(dao.findById(1)).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Finds all entities")
    @Test
    void findAll() {
        Author mFowler = new Author(1, "Martin", "Fowler");
        Author rMartin = new Author(10, "Robert", "Martin");
        assertThat(dao.findAll())
                .hasSize(23)
                .contains(mFowler)
                .contains(rMartin);
    }

    @DisplayName("Adds new entity")
    @Test
    void insert() {
        Author expected = new Author(24, "Vladimir", "Veselov");
        dao.insert(expected);
        assertThat(dao.findById(expected.getId())).isEqualToComparingFieldByField(expected);
        assertThat(dao.count()).isEqualTo(24);
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        Author expected = new Author(24, "Vladimir", "Veselov");
        dao.insert(expected);
        dao.update(new Author(24, "Alexander", "Veselov"));
        Author updated = dao.findById(24);
        assertThat(updated)
                .extracting(Author::getFirstName, Author::getLastName)
                .containsExactly("Alexander", "Veselov");
    }

    @DisplayName("Can't delete entity because of relations")
    @Test
    void delete() {
        assertThatThrownBy(() -> {
            dao.delete(1);
        }).hasMessageContaining("constraint violation");
    }
}