package com.v1690117.app.dao;

import com.v1690117.app.bee.MongockConfig;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@DataMongoTest
@Import(MongockConfig.class)
class GenreRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 10;

    @Autowired
    MongoTemplate mongo;

    @Autowired
    private GenreRepository dao;

    @DisplayName("Finds entity by id")
    @Test
    void findById() {
        Genre expected = new Genre(2L, "comedy");
        assertThat(dao.findById(2L).get()).isEqualToComparingFieldByField(expected);
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
        Genre expected = new Genre(100L, "anything");
        Genre inserted = dao.save(expected);
        assertThat(
                mongo.find(
                        query(
                                where("_id").is(inserted.getId())
                        ),
                        Genre.class
                ).get(0)
        ).extracting(Genre::getName).isEqualTo(expected.getName());
    }

    @DisplayName("Updates entity")
    @Test
    void update() {
        Genre first = dao.findById(1L).get();
        assertThat(first.getName()).isEqualTo("drama");
        dao.save(new Genre(first.getId(), "melodrama"));
        first = mongo.find(
                query(
                        where("_id").is(first.getId())
                ),
                Genre.class
        ).get(0);
        assertThat(first.getName()).isEqualTo("melodrama");
    }

    @DisplayName("Deletes entity")
    @Test
    void delete() {
        Genre genre = mongo.find(
                query(
                        where("_id").is(10L)
                ),
                Genre.class
        ).get(0);
        assertThat(genre).isNotNull();
        dao.delete(genre);
        assertThat(
                mongo.find(
                        query(
                                where("_id").is(10L)
                        ),
                        Genre.class
                )
        ).isNotNull().hasSize(0);
    }
}