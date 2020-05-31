package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.dao.jdbc.mappers.AuthorMapperProvider;
import com.v1690117.app.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapperProvider authorMapperProvider;

    @Override
    public long count() {
        return jdbc.queryForObject(
                "select count(author_id) from authors``",
                Collections.emptyMap(),
                Long.class
        );
    }

    @Override
    public Author findById(long id) {
        return jdbc.queryForObject(
                "select author_id, first_name, last_name from authors where author_id = :id",
                Collections.singletonMap("id", id),
                authorMapperProvider.mapper()
        );
    }

    @Override
    public List<Author> findAll() {
        return jdbc.query(
                "select author_id, first_name, last_name from authors",
                authorMapperProvider.mapper()
        );
    }

    @Override
    public void insert(Author author) {
        jdbc.update(
                "insert into authors (author_id, first_name, last_name) "
                        + "values  (:id, :firstName, :lastName)",
                author.map()
        );
    }

    @Override
    public void update(Author author) {
        jdbc.update(
                "update authors set first_name = :firstName, last_name = :lastName where author_id = :id",
                author.map()
        );
    }

    @Override
    public void delete(long id) {
        jdbc.update(
                "delete from authors where author_id = :id",
                Collections.singletonMap("id", id)
        );
    }
}
