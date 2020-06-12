package com.v1690117.app.services;

import com.v1690117.app.Application;
import com.v1690117.app.dao.AuthorRepository;
import com.v1690117.app.dao.BookRepository;
import com.v1690117.app.dao.GenreRepository;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Genre service")
@SpringBootTest(classes = Application.class)
public class DefaultGenreServiceTest {
    @MockBean
    private GenreRepository dao;
    @Autowired
    private GenreService service;
    @MockBean
    private BookRepository bookRepository;
    @MockBean
    private AuthorRepository authorRepository;

    @Test
    void findAll() {
        List<Genre> testGenres = getTestGenres();
        given(dao.findAll()).willReturn(testGenres);
        assertThat(service.findAll()).isNotNull()
                .containsAll(testGenres)
                .hasSize(3);
    }

    @Test
    void findById() {
        Genre expected = getDrama();
        given(dao.findById(1L)).willReturn(Optional.of(expected));
        assertThat(service.findById(1)).isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void insert() {
        Genre inserting = getDrama();
        service.insert(inserting);
        verify(dao, times(1)).save(inserting);

        Genre anotherInserting = getOpposition();
        service.insert(anotherInserting);

        assertThatThrownBy(
                () -> service.insert(
                        new Genre(
                                100L,
                                null
                        )
                )
        ).hasMessageContaining("empty");
    }

    @Test
    void update() {
        Genre original = getDrama();
        given(dao.findById(1L)).willReturn(Optional.of(original));
        Genre updating = new Genre(
                1L,
                "comedy"
        );
        service.update(updating);
        verify(dao, times(1)).save(updating);

        service.update(
                new Genre(
                        1L,
                        null
                )
        );
        verify(dao, times(1)).save(
                original
        );
    }

    @Test
    void delete() {
        Genre expected = getDrama();
        given(dao.findById(1L)).willReturn(Optional.of(expected));
        service.delete(1L);
        verify(dao, times(1)).delete(any());
    }

    public List<Genre> getTestGenres() {
        List<Genre> genres = new LinkedList<>();
        genres.add(
                getDrama()
        );
        genres.add(
                getOpposition()
        );
        genres.add(
                getSoftwareDelopment()
        );
        return genres;
    }

    private Genre getDrama() {
        return new Genre(
                1L,
                "drama"
        );
    }

    private Genre getOpposition() {
        return new Genre(
                9L,
                "opposition"
        );
    }

    private Genre getSoftwareDelopment() {
        return new Genre(
                8L,
                "software development"
        );
    }
}