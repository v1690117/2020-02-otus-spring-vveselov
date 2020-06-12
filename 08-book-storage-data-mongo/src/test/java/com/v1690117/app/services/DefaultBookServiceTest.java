package com.v1690117.app.services;

import com.v1690117.app.Application;
import com.v1690117.app.dao.AuthorRepository;
import com.v1690117.app.dao.BookRepository;
import com.v1690117.app.dao.GenreRepository;
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
import java.util.Optional;

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
    private BookRepository dao;
    @MockBean
    private AuthorRepository authorRepository;
    @MockBean
    private GenreRepository genreRepository;

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
        given(dao.findById(1L)).willReturn(Optional.of(expected));
        assertThat(service.findById(1)).isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void insert() {
        Book inserting = getFirstBook();
        given(authorRepository.findById(1L)).willReturn(Optional.of(inserting.getAuthors().get(0)));
        given(genreRepository.findById(1L)).willReturn(Optional.of(inserting.getGenres().get(0)));
        service.insert(
                inserting.getTitle(),
                inserting.getAnnotation(),
                inserting.getYear(),
                new long[]{inserting.getAuthors().get(0).getId()},
                new long[]{inserting.getGenres().get(0).getId()}
        );
        verify(dao, times(1)).save(any());

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
        given(dao.findById(1L)).willReturn(Optional.of(original));
        given(authorRepository.findById(1L)).willReturn(Optional.of(original.getAuthors().get(0)));
        given(genreRepository.findById(1L)).willReturn(Optional.of(original.getGenres().get(0)));
        service.update(
                1,
                "Test title",
                "Test annotation",
                "2020",
                new long[]{1L},
                new long[]{1L},
                "test comment"
        );
        verify(dao, times(1)).save(original);
    }

    @Test
    void delete() {
        Book book = getFirstBook();
        given(dao.findById(1L)).willReturn(Optional.of(book));
        service.delete(1);
        verify(dao, times(1)).delete(book);
    }

    public List<Book> getTestBooks() {
        List<Book> books = new LinkedList<>();
        books.add(getFirstBook());
        books.add(getSecondBook());
        return books;
    }

    private Book getFirstBook() {
        List<Author> authors = new LinkedList<>();
        authors.add(new Author(1L, "Petr", "Ivanov"));
        List<Genre> genres = new LinkedList<>();
        genres.add(new Genre(1L, "testing"));
        return new Book(
                1L,
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
                1L,
                "Second Test",
                "Just another test book",
                "2021",
                Collections.singletonList(
                        new Author(
                                1L,
                                "Petr",
                                "Ivanov"
                        )
                ),
                Collections.singletonList(
                        new Genre(
                                2L,
                                "another testing genre"
                        )
                ),
                Collections.emptyList()
        );
    }
}