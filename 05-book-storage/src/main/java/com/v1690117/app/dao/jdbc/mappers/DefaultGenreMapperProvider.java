package com.v1690117.app.dao.jdbc.mappers;

import com.v1690117.app.model.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class DefaultGenreMapperProvider implements GenreMapperProvider {
    public RowMapper<Genre> mapper() {
        return (ResultSet resultSet, int i) -> new Genre(
                resultSet.getLong("genre_id"),
                resultSet.getString("name")
        );
    }
}
