package com.v1690117.app.dao.jdbc.mappers;

import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.Collections;

@Component
public class DefaultBookMapperProvider implements BookMapperProvider {
    public RowMapper<Book> mapper() {
        return (ResultSet resultSet, int i) -> {
            Author author = new Author(
                    resultSet.getLong("author_id"),
                    resultSet.getString("first_name"),
                    resultSet.getString("last_name")
            );
            Genre genre = new Genre(
                    resultSet.getLong("genre_id"),
                    resultSet.getString("name")
            );
            return new Book(
                    resultSet.getLong("book_id"),
                    resultSet.getString("title"),
                    resultSet.getString("annotation"),
                    resultSet.getString("year"),
                    Collections.singletonList(author),
                    Collections.singletonList(genre)
            );
        };
    }
}
