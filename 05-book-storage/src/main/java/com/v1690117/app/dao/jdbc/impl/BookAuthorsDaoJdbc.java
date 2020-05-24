package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.BookAuthorsDao;
import com.v1690117.app.dao.jdbc.mappers.AuthorMapperProvider;
import com.v1690117.app.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class BookAuthorsDaoJdbc implements BookAuthorsDao {
    private final NamedParameterJdbcOperations jdbc;
    private final AuthorMapperProvider authorMapperProvider;

    @Override
    public List<Author> findAuthorsByBookId(long bookId) {
        return jdbc.query(
                "select a.author_id, a.first_name, a.last_name "
                        + "from authors a "
                        + "inner join books_authors ba on a.author_id = ba.author_id "
                        + "where ba.book_id = :id",
                Collections.singletonMap("id", bookId),
                authorMapperProvider.mapper()
        );
    }

    @Override
    public void addAuthorForBook(long bookId, long authorId) {
        jdbc.update(
                "insert into books_authors (book_id, author_id) "
                        + "values  (:bookId, :authorId)",
                map(bookId, authorId)
        );
    }

    @Override
    public void deleteAuthorForBook(long bookId, long authorId) {
        jdbc.update(
                "delete from books_authors where book_id = :bookId and author_id = :authorId",
                map(bookId, authorId)
        );
    }

    private Map<String, Long> map(long bookId, long authorId) {
        Map<String, Long> map = new HashMap<>();
        map.put("bookId", bookId);
        map.put("authorId", authorId);
        return map;
    }
}
