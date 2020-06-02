package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@Import(BookRepository.class)
class BookRepositoryTest {
    public static final int EXPECTED_ENTITIES_NUMBER = 13;

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private BookDao dao;

    @Test
    void count() {
        assertThat(dao.count()).isEqualTo(EXPECTED_ENTITIES_NUMBER);
    }

    @Test
    void findById() {
        Book expected = getFirstBook();
        assertThat(dao.findById(1)).
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
        dao.insert(testBook);
        assertThat(manager.find(Book.class, 14L))
                .isNotNull().isEqualToComparingFieldByField(testBook);
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
        dao.delete(1);
        assertThat(dao.count()).isEqualTo(12);
    }

    private Book getFirstBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(getSoftwareDevelopment());
        genres.add(getOppositionGenre());
        return new Book(
                1,
                "Elegant Objects",
                " ... Elegant Objects ...",
                "2016",
                Collections.singletonList(
                        getSecondAuthor()
                ),
                genres
        );
    }

    private Book getTestBook() {
        List<Genre> genres = new ArrayList<>();
        genres.add(getSoftwareDevelopment());
        return new Book(
                14,
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