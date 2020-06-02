package com.v1690117.app.services;

import com.v1690117.app.Application;
import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.LinkedList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Genre service")
@SpringBootTest(classes = Application.class)
public class DefaultGenreServiceTest {
    @MockBean
    private GenreDao dao;
    @Autowired
    private GenreService service;

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
        given(dao.findById(1)).willReturn(expected);
        assertThat(service.findById(1)).isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void insert() {
        Genre inserting = getDrama();
        given(dao.count()).willReturn(0L);
        service.insert(inserting);
        verify(dao, times(1)).insert(inserting);

        Genre anotherInserting = getOpposition();
        given(dao.count()).willReturn(1L);
        service.insert(anotherInserting);

        assertThatThrownBy(
                () -> service.insert(
                        new Genre(
                                100,
                                null
                        )
                )
        ).hasMessageContaining("empty");
    }

    @Test
    void update() {
        Genre original = getDrama();
        given(dao.findById(1)).willReturn(original);
        Genre updating = new Genre(
                1,
                "comedy"
        );
        service.update(updating);
        verify(dao, times(1)).update(updating);

        service.update(
                new Genre(
                        1,
                        null
                )
        );
        verify(dao, times(1)).update(
                original
        );
    }

    @Test
    void delete() {
        service.delete(1);
        verify(dao, times(1)).delete(1);
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
                1,
                "drama"
        );
    }

    private Genre getOpposition() {
        return new Genre(
                9,
                "opposition"
        );
    }

    private Genre getSoftwareDelopment() {
        return new Genre(
                8,
                "software development"
        );
    }
}