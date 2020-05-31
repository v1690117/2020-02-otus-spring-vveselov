package com.v1690117.app.dao.jdbc.impl;

import com.v1690117.app.dao.BookDao;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book DAO")
@RunWith(SpringRunner.class)
@JdbcTest
@ComponentScan("com.v1690117.app.dao.jdbc")
@ActiveProfiles("jdbc")
class BookDaoJdbcTest {
    @Autowired
    private BookDao dao;

    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(13);
    }

    @Test
    void findById() {
        Book expected = getFirstBook();
        assertThat(dao.findById(1)).isEqualToComparingFieldByField(expected);
    }

    @Test
    void findAll() {
        assertThat(dao.findAll())
                .hasSize(13)
                .contains(getFirstBook());
    }

    @Test
    void insert() {
        Book testBook = getTestBook();
        dao.insert(testBook);
        assertThat(dao.findAll())
                .hasSize(14)
                .contains(testBook);
    }

    @Test
    void update() {
        Book replacement = getTestBook();
        dao.update(
                new Book(
                        1,
                        replacement.getTitle(),
                        replacement.getAnnotation(),
                        replacement.getYear(),
                        replacement.getAuthors(),
                        replacement.getGenres()
                )
        );
        assertThat(dao.findById(1))
                .extracting(
                        Book::getId,
                        Book::getTitle,
                        Book::getAnnotation,
                        Book::getYear,
                        Book::getAuthors,
                        Book::getGenres
                )
                .containsExactly(
                        1L,
                        replacement.getTitle(),
                        replacement.getAnnotation(),
                        replacement.getYear(),
                        replacement.getAuthors(),
                        replacement.getGenres()
                );
    }

    @Test
    void delete() {
        dao.delete(1);
        assertThat(dao.count()).isEqualTo(12);
    }

    private Book getFirstBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(getSoftwareDevelopment());
        genres.add(getOppositionGenre());
        Book book = new Book(
                1,
                "Elegant Objects",
                " ... Elegant Objects ...",
                "2016",
                Collections.singletonList(
                        getSecondAuthor()
                ),
                genres
        );
        return book;
    }

    private Book getTestBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(getSoftwareDevelopment());
        Book book = new Book(
                14,
                "Clean Agile: Back to Basics",
                "Agile Values and Principles for a New Generation",
                "2019",
                Collections.singletonList(
                        getTensAuthor()
                ),
                genres
        );
        return book;
    }

    private Author getSecondAuthor() {
        return new Author(
                2,
                "Egor",
                "Bugaenko"
        );
    }

    private Author getTensAuthor() {
        return new Author(
                10,
                "Robert",
                "Martin"
        );
    }

    private Genre getOppositionGenre() {
        return new Genre(9, "opposition");
    }

    private Genre getSoftwareDevelopment() {
        return new Genre(8, "software development");
    }
}