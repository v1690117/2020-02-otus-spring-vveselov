package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class BookRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 13;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private BookRepository dao;

    @Test
    void findById() {
        Book expected = getFirstBook();
        assertThat(dao.findById(1L).get()).
                extracting(
                        Book::getTitle,
                        Book::getAnnotation,
                        Book::getYear
                )
                .containsExactly(
                        expected.getTitle(),
                        expected.getAnnotation(),
                        expected.getYear()
                );
    }

    @Test
    void findAll() {
        assertThat(dao.findAll())
                .hasSize(EXPECTED_ENTITIES_NUMBER);
    }

    @Test
    void insert() {
        Book testBook = getTestBook();
        Book inserted = dao.save(testBook);
        assertThat(manager.find(Book.class, inserted.getId()))
                .isNotNull().extracting(
                Book::getTitle,
                Book::getAnnotation,
                Book::getYear,
                Book::getAuthors,
                Book::getGenres
        ).containsExactly(
                testBook.getTitle(),
                testBook.getAnnotation(),
                testBook.getYear(),
                testBook.getAuthors(),
                testBook.getGenres()
        );
    }

    @Test
    void update() {
        Book replacement = getTestBook();
        dao.save(
                new Book(
                        1L,
                        replacement.getTitle(),
                        replacement.getAnnotation(),
                        replacement.getYear(),
                        replacement.getAuthors(),
                        replacement.getGenres(),
                        replacement.getComments()
                )
        );
        assertThat(dao.findById(1L).get())
                .extracting(
                        Book::getId,
                        Book::getTitle,
                        Book::getAnnotation,
                        Book::getYear
                )
                .containsExactly(
                        1L,
                        replacement.getTitle(),
                        replacement.getAnnotation(),
                        replacement.getYear()
                );
    }

    @Test
    void delete() {
        Book book = manager.find(Book.class, 1L);
        dao.delete(book);
        assertThat(manager.find(Book.class, 1L)).isNull();
    }

    private Book getFirstBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(getSoftwareDevelopment());
        genres.add(getOppositionGenre());
        return new Book(
                1L,
                "Elegant Objects",
                " ... Elegant Objects ...",
                "2016",
                Collections.singletonList(
                        getSecondAuthor()
                ),
                genres,
                Collections.emptyList()
        );
    }

    private Book getTestBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(getSoftwareDevelopment());
        return new Book(
                "Clean Agile: Back to Basics",
                "Agile Values and Principles for a New Generation",
                "2019",
                Collections.singletonList(
                        getTensAuthor()
                ),
                genres
        );
    }

    private Author getSecondAuthor() {
        return new Author(
                2L,
                "Egor",
                "Bugaenko"
        );
    }

    private Author getTensAuthor() {
        return new Author(
                10L,
                "Robert",
                "Martin"
        );
    }

    private Genre getOppositionGenre() {
        return new Genre(9L, "opposition");
    }

    private Genre getSoftwareDevelopment() {
        return new Genre(8L, "software development");
    }
}