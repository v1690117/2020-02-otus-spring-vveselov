package com.v1690117.app.services;

import com.v1690117.app.Application;
import com.v1690117.app.dao.AuthorRepository;
import com.v1690117.app.dao.BookRepository;
import com.v1690117.app.dao.GenreRepository;
import com.v1690117.app.model.Author;
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

@DisplayName("Author service")
@SpringBootTest(classes = Application.class)
public class DefaultAuthorServiceTest {
    @MockBean
    private AuthorRepository dao;
    @Autowired
    private AuthorService service;

    // todo temp decision because of jpaMapping IllegalArgumentException
    @MockBean
    private BookRepository bookRepository;
    // todo temp decision because of jpaMapping IllegalArgumentException
    @MockBean
    private GenreRepository genreRepository;

    @Test
    void findAll() {
        List<Author> testAuthors = getTestAuthors();
        given(dao.findAll()).willReturn(testAuthors);
        assertThat(service.findAll()).isNotNull()
                .containsAll(testAuthors)
                .hasSize(2);
    }

    @Test
    void findById() {
        Author expected = getPushkin();
        given(dao.findById(1L)).willReturn(Optional.of(expected));
        assertThat(service.findById(1)).isNotNull()
                .isEqualToComparingFieldByField(expected);
    }

    @Test
    void insert() {
        Author inserting = getPushkin();
        service.insert(inserting);
        verify(dao, times(1)).save(inserting);

        Author anotherInserting = getTolstoy();
        service.insert(anotherInserting);

        assertThatThrownBy(
                () -> service.insert(
                        new Author(
                                null,
                                null
                        )
                )
        ).hasMessageContaining("empty");
    }

    @Test
    void update() {
        Author original = getPushkin();
        given(dao.findById(1L)).willReturn(Optional.of(original));
        Author expected = new Author(
                1L,
                "Nikolay",
                "Gogol"
        );
        service.update(expected);
        verify(dao, times(1)).save(expected);

        service.update(
                new Author(
                        1L,
                        null,
                        null
                )
        );
        verify(dao, times(1)).save(new Author(
                1L,
                original.getFirstName(),
                original.getLastName()
        ));
    }

    @Test
    void delete() {
        Author author = getPushkin();
        given(dao.findById(1L)).willReturn(Optional.of(author));
        service.delete(1L);
        verify(dao, times(1)).delete(any());
    }

    public List<Author> getTestAuthors() {
        List<Author> authors = new LinkedList<>();
        authors.add(
                getPushkin()
        );
        authors.add(
                getTolstoy()
        );
        return authors;
    }

    private Author getPushkin() {
        return new Author(
                1L,
                "Alexander",
                "Pushkin"
        );
    }

    private Author getTolstoy() {
        return new Author(
                2L,
                "Lev",
                "Tolstoy"
        );
    }
}