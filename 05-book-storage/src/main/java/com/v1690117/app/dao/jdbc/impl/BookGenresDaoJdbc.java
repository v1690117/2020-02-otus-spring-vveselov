package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.BookGenresDao;
import com.v1690117.app.dao.jdbc.mappers.DefaultGenreMapperProvider;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Repository
public class BookGenresDaoJdbc implements BookGenresDao {
    private final NamedParameterJdbcOperations jdbc;
    private final DefaultGenreMapperProvider genreMapperProvider;

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
                genreMapperProvider.mapper()
        );
    }

    @Override
    public void addGenreForBook(long bookId, long genreId) {
        jdbc.update(
                "insert into books_genres (book_id, genre_id) "
                        + "values  (:bookId, :genreId)",
                map(bookId, genreId)
        );
    }

    @Override
    public void deleteGenreForBook(long bookId, long genreId) {
        jdbc.update(
                "delete from books_genres where book_id = :bookId and genre_id = :genreId",
                map(bookId, genreId)
        );
    }

    private Map<String, Long> map(long bookId, long genreId) {
        Map<String, Long> map = new HashMap<>();
        map.put("bookId", bookId);
        map.put("genreId", genreId);
        return map;
    }
}
