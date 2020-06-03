package com.v1690117.app.services;

import com.v1690117.app.Application;
import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Book service")
@SpringBootTest(classes = Application.class)
public class DefaultBookServiceTest {
    @MockBean
    private BookDao dao;
    @MockBean
    private AuthorDao authorDao;
    @MockBean
    private GenreDao genreDao;

    @Autowired
    private BookService service;

    @Test
    void findAll() {
        List<Book> testBooks = getTestBooks();
        given(dao.findAll()).willReturn(testBooks);
        assertThat(service.findAll()).isNotNull()
                .containsAll(testBooks)
                .hasSize(2);
    }

    @Test
    void findById() {
        Book expected = getFirstBook();
        given(dao.findById(1)).willReturn(expected);
        assertThat(service.findById(1)).isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void insert() {
        Book inserting = getFirstBook();
        given(authorDao.findById(1L)).willReturn(inserting.getAuthors().get(0));
        given(genreDao.findById(1L)).willReturn(inserting.getGenres().get(0));
        service.insert(
                inserting.getTitle(),
                inserting.getAnnotation(),
                inserting.getYear(),
                new long[]{inserting.getAuthors().get(0).getId()},
                new long[]{inserting.getGenres().get(0).getId()}
        );
        verify(dao, times(1)).insert(any());

        assertThatThrownBy(
                () -> service.insert(
                        "",
                        "",
                        "",
                        new long[]{},
                        new long[]{}
                )
        ).hasMessageContaining("empty");
    }

    @Test
    void update() {
        Book original = getFirstBook();
        given(dao.findById(1)).willReturn(original);
        given(authorDao.findById(1L)).willReturn(original.getAuthors().get(0));
        given(genreDao.findById(1L)).willReturn(original.getGenres().get(0));
        service.update(
                1,
                "Test title",
                "Test annotation",
                "2020",
                new long[]{1L},
                new long[]{1L},
                "test comment"
        );
        verify(dao, times(1)).update(original);
    }

    @Test
    void delete() {
        service.delete(1);
        verify(dao, times(1)).delete(1);
    }

    public List<Book> getTestBooks() {
        List<Book> books = new LinkedList<>();
        books.add(getFirstBook());
        books.add(getSecondBook());
        return books;
    }

    private Book getFirstBook() {
        List<Author> authors = new LinkedList<>();
        authors.add(new Author(1, "Petr", "Ivanov"));
        List<Genre> genres = new LinkedList<>();
        genres.add(new Genre(1, "testing"));
        return new Book(
                1,
                "Test",
                "Just test book",
                "2020",
                authors,
                genres,
                new LinkedList<>()
        );
    }

    private Book getSecondBook() {
        return new Book(
                1,
                "Second Test",
                "Just another test book",
                "2021",
                Collections.singletonList(
                        new Author(
                                1,
                                "Petr",
                                "Ivanov"
                        )
                ),
                Collections.singletonList(
                        new Genre(
                                2,
                                "another testing genre"
                        )
                ),
                Collections.emptyList()
        );
    }
}