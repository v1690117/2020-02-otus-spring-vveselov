package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(AuthorRepository.class)
@ActiveProfiles("hibernate")
class AuthorRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 24;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private AuthorDao dao;

    @DisplayName("Counts entities")
    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(EXPECTED_ENTITIES_NUMBER);
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
                .hasSize(EXPECTED_ENTITIES_NUMBER)
                .contains(mFowler)
                .contains(rMartin);
    }

    @DisplayName("Adds new entity")
    @Test
    void insert() {
        Author expected = dao.findById(dao.count() + 1);
        expected = new Author(dao.count() + 1, "Vladimir", "Veselov");
        dao.insert(expected);
        assertThat(manager.find(Author.class, expected.getId())).isEqualToComparingFieldByField(expected);
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        long id = 24L;
        dao.update(new Author(id, "Vladimir", "Veselov"));
        Author updated = manager.find(Author.class, id);
        assertThat(updated)
                .extracting(Author::getFirstName, Author::getLastName)
                .containsExactly("Vladimir", "Veselov");
    }

    @DisplayName("Deletes entity")
    @Test
    void delete() {
        long id = 24L;
        assertThat(manager.find(Author.class, id)).isNotNull();
        dao.delete(id);
        assertThat(manager.find(Author.class, id)).isNull();
    }
}