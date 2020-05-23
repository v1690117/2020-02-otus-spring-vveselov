package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookAuthorsDaoJdbc implements BookAuthorsDao {
    private final NamedParameterJdbcOperations jdbc;

    private final RowMapper<Author> authorRowMapper = (ResultSet resultSet, int i) -> new Author(
            resultSet.getLong("author_id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name")
    );

    @Override
    public List<Author> findAuthorsByBookId(long bookId) {
        return jdbc.query(
                "select a.author_id, a.first_name, a.last_name "
                        + "from authors a "
                        + "inner join books_authors ba on a.author_id = ba.author_id "
                        + "where ba.book_id = :id",
                Collections.singletonMap("id", bookId),
                authorRowMapper
        );
    }

    @Override
    public void addAuthorForBook(Book book, Author author) {

    }

    @Override
    public void deleteAuthorForBook(Book book, Author author) {

    }
}
