package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.BookAuthorsDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.BookGenresDao;
import com.v1690117.app.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final BookGenresDao bookGenresDao;
    private final BookAuthorsDao bookAuthorsDao;
    private final NamedParameterJdbcOperations jdbc;

    private final RowMapper<Book> mapper = (ResultSet resultSet, int i) -> {
        long bookId = resultSet.getLong("book_id");
        return new Book(
                bookId,
                resultSet.getString("title"),
                resultSet.getString("annotation"),
                resultSet.getString("year"),
                getBookAuthorsDao().findAuthorsByBookId(bookId),
                getBookGenresDao().findGenresByBookId(bookId)
        );
    };

    private BookGenresDao getBookGenresDao() {
        return bookGenresDao;
    }

    private BookAuthorsDao getBookAuthorsDao() {
        return bookAuthorsDao;
    }

    @Override
    public long count() {
        return jdbc.queryForObject(
                "select count(*) from books",
                Collections.emptyMap(),
                Long.class
        );
    }


    @Override
    public void insert(Book book) {
        jdbc.update(
                "insert into books (book_id, title) values  (:id, :title)",
                book.map()
        );
    }

    @Override
    public void update(Book book) {
        jdbc.update(
                "update books set title = :title where book_id = :id",
                book.map()
        );
    }

    @Override
    public void delete(long id) {
        jdbc.update(
                "delete from books where book_id = :id",
                Collections.singletonMap("id", id)
        );
    }

    @Override
    public Book findById(long id) {
        return jdbc.queryForObject(
                "select * from books where book_id = :id",
                Collections.singletonMap("id", id),
                mapper
        );
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query(
                "select * from books",
                mapper
        );
    }
}
