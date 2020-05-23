package com.v1690117.app.dao;

import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookGenresDaoJdbc implements BookGenresDao {
    private final NamedParameterJdbcOperations jdbc;

    private final RowMapper<Genre> genreRowMapper = (ResultSet resultSet, int i) -> new Genre(
            resultSet.getLong("genre_id"),
            resultSet.getString("name")
    );

    @Override
    public long count() {
        return 0;
    }

    @Override
    public List<Genre> findGenresByBookId(long bookId) {
        return jdbc.query(
                "select g.genre_id genre_id, g.name name "
                        + "from genres g "
                        + "inner join books_genres bg on g.genre_id = bg.genre_id "
                        + "where bg.book_id = :id",
                Collections.singletonMap("id", bookId),
                genreRowMapper
        );
    }

    @Override
    public void addGenreForBook(Book book, Genre genre) {

    }

    @Override
    public void deleteGenreForBook(Book book, Genre genre) {

    }
}
