package com.v1690117.app.dao.jdbc.mappers;

import com.v1690117.app.model.Author;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;

@Component
public class DefaultAuthorMapperProvider implements AuthorMapperProvider {
    public RowMapper<Author> mapper() {
        return (ResultSet resultSet, int i) -> new Author(
                resultSet.getLong("author_id"),
                resultSet.getString("first_name"),
                resultSet.getString("last_name")
        );
    }
}
