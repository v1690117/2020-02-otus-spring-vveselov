package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.BookAuthorsDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.BookGenresDao;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
                "insert into books (book_id, title, annotation, year) values  (:id, :title, :annotation, :year)",
                book.map()
        );
        book.getAuthors().forEach(
                author -> bookAuthorsDao.addAuthorForBook(
                        book.getId(),
                        author.getId()
                )
        );
        book.getGenres().forEach(
                genre -> bookGenresDao.addGenreForBook(
                        book.getId(),
                        genre.getId()
                )
        );
    }

    @Override
    public void update(Book book) {
        Book prevBook = this.findById(book.getId());
        jdbc.update(
                "update books set title = :title, annotation = :annotation, year = :year where book_id = :id",
                book.map()
        );
        updateAuthors(book, prevBook);
        updateGenres(book, prevBook);
    }

    @Override
    public void delete(long id) {
        bookAuthorsDao.findAuthorsByBookId(id)
                .forEach(
                        author -> bookAuthorsDao.deleteAuthorForBook(
                                id,
                                author.getId()
                        )
                );
        bookGenresDao.findGenresByBookId(id)
                .forEach(
                        genre -> bookGenresDao.deleteGenreForBook(
                                id,
                                genre.getId()
                        )
                );
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

    private void updateAuthors(Book book, Book prevBook) {
        removeAuthorsForBook(
                book.getId(),
                getRemovedAuthors(book, prevBook)
        );
        addAuthorsForBook(
                book.getId(),
                getAddedAuthors(book, prevBook)
        );
    }

    private List<Author> getAddedAuthors(Book book, Book prevBook) {
        return book.getAuthors()
                .stream()
                .filter(author -> !prevBook.getAuthors().contains(author))
                .collect(Collectors.toList());
    }

    private List<Author> getRemovedAuthors(Book book, Book prevBook) {
        return prevBook.getAuthors()
                .stream()
                .filter(author -> !book.getAuthors().contains(author))
                .collect(Collectors.toList());
    }

    private void removeAuthorsForBook(long bookId, List<Author> authors) {
        authors.forEach(author ->
                bookAuthorsDao.deleteAuthorForBook(
                        bookId,
                        author.getId()
                )
        );
    }

    private void addAuthorsForBook(long bookId, List<Author> authors) {
        authors.forEach(author ->
                bookAuthorsDao.addAuthorForBook(
                        bookId,
                        author.getId()
                )
        );
    }

    private void updateGenres(Book book, Book prevBook) {
        removeGenresForBook(
                book.getId(),
                getRemovedGenres(book, prevBook)
        );
        addGenresForBook(
                book.getId(),
                getAddedGenres(book, prevBook)
        );
    }

    private List<Genre> getAddedGenres(Book book, Book prevBook) {
        return book.getGenres()
                .stream()
                .filter(genre -> !prevBook.getGenres().contains(genre))
                .collect(Collectors.toList());
    }

    private List<Genre> getRemovedGenres(Book book, Book prevBook) {
        return prevBook.getGenres()
                .stream()
                .filter(genre -> !book.getGenres().contains(genre))
                .collect(Collectors.toList());
    }

    private void removeGenresForBook(long bookId, List<Genre> genres) {
        genres.forEach(genre ->
                bookGenresDao.deleteGenreForBook(
                        bookId,
                        genre.getId()
                )
        );
    }

    private void addGenresForBook(long bookId, List<Genre> genres) {
        genres.forEach(genre ->
                bookGenresDao.addGenreForBook(
                        bookId,
                        genre.getId()
                )
        );
    }
}
