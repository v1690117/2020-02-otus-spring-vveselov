package com.v1690117.app.dao;

import com.v1690117.app.dao.jdbc.mappers.GenreMapperProvider;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class GenreDaoJdbc implements GenreDao {
    private final NamedParameterJdbcOperations jdbc;
    private final GenreMapperProvider genreMapperProvider;

    @Override
    public long count() {
        return jdbc.queryForObject(
                "select count(*) from genres",
                Collections.emptyMap(),
                Long.class
        );
    }

    @Override
    public Genre findById(long id) {
        return jdbc.queryForObject(
                "select * from genres where genre_id = :id",
                Collections.singletonMap("id", id),
                genreMapperProvider.mapper()
        );
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query(
                "select * from genres",
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
        // todo check rels
        jdbc.update(
                "delete from genres where genre_id = :id",
                Collections.singletonMap("id", id)
        );
    }
}
