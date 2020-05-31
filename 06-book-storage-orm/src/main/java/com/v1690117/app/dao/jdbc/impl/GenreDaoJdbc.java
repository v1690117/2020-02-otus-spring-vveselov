package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.dao.jdbc.mappers.GenreMapperProvider;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
@Profile("jdbc")
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;
    private final GenreMapperProvider genreMapperProvider;

    @Override
    public long count() {
        return jdbc.queryForObject(
                "select count(genre_id) from genres",
                Collections.emptyMap(),
                Long.class
        );
    }

    @Override
    public Genre findById(long id) {
        return jdbc.queryForObject(
                "select genre_id, name from genres where genre_id = :id",
                Collections.singletonMap("id", id),
                genreMapperProvider.mapper()
        );
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query(
                "select genre_id, name from genres",
                genreMapperProvider.mapper()
        );
    }

    @Override
    public void insert(Genre genre) {
        jdbc.update(
                "insert into genres (genre_id, name) values  (:id, :name)",
                genre.map()
        );
    }

    @Override
    public void update(Genre genre) {
        jdbc.update(
                "update genres set name = :name where genre_id = :id",
                genre.map()
        );
    }

    @Override
    public void delete(long id) {
        jdbc.update(
                "delete from genres where genre_id = :id",
                Collections.singletonMap("id", id)
        );
    }
}
