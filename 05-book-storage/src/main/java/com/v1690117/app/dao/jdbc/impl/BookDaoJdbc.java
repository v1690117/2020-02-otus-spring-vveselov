package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.BookAuthorsDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.BookGenresDao;
import com.v1690117.app.dao.jdbc.mappers.BookMapperProvider;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class BookDaoJdbc implements BookDao {
    private final BookGenresDao bookGenresDao;
    private final BookAuthorsDao bookAuthorsDao;
    private final NamedParameterJdbcOperations jdbc;
    private final BookMapperProvider mapperProvider;

    private static final String SELECT_BOOKS = "select "
            + "b.book_id, b.title, b.year, b.annotation, b.year, "
            + "ba.author_id, ba.first_name, ba.last_name, "
            + "bg.genre_id, bg.name "
            + "from books b "
            + "left join (select ba.author_id, ba.book_id, a.first_name, a.last_name "
            + "  from books_authors ba "
            + "  left join authors a on ba.author_id = a.author_id) ba "
            + "on b.book_id = ba.book_id "
            + "  left join (select bg.genre_id, bg.book_id, g.name "
            + "  from books_genres bg "
            + "left join genres g on bg.genre_id = g.genre_id) bg "
            + "on b.book_id = bg.book_id ";

    @Override
    public long count() {
        return jdbc.queryForObject(
                "select count(book_id) from books",
                Collections.emptyMap(),
                Long.class
        );
    }

    @Override
    public Book findById(long id) {
        List<Book> books = mergeBooks(
                jdbc.query(
                        SELECT_BOOKS
                                + "where b.book_id = :id",
                        Collections.singletonMap("id", id),
                        mapperProvider.mapper()
                )
        );
        return books.get(0);
    }

    @Override
    public List<Book> findAll() {
        return mergeBooks(
                jdbc.query(
                        SELECT_BOOKS,
                        mapperProvider.mapper()
                )
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

    private List<Book> mergeBooks(List<Book> bookList) {
        Map<Long, Book> books = new LinkedHashMap<>();
        bookList.forEach(book -> {
            if (books.containsKey(book.getId())) {
                Book b = books.get(book.getId());
                b.getAuthors().addAll(book.getAuthors());
                b.getGenres().addAll(book.getGenres());
            } else {
                books.put(
                        book.getId(),
                        new Book(book)
                );
            }
        });
        return books.keySet()
                .stream()
                .map(bookId -> {
                            Book book = books.get(bookId);
                            return new Book(
                                    bookId,
                                    book.getTitle(),
                                    book.getAnnotation(),
                                    book.getYear(),
                                    new LinkedList<>(
                                            new LinkedHashSet<>(
                                                    book.getAuthors()
                                            )
                                    ),
                                    new LinkedList<>(
                                            new LinkedHashSet<>(
                                                    book.getGenres()
                                            )
                                    )
                            );
                        }
                )
                .collect(Collectors.toList());
    }
}
