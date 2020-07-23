package com.v1690117.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1690117.app.model.Genre;
import com.v1690117.app.services.GenreService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.LinkedList;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GenreController.class)
class GenreControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private GenreService service;

    @Test
    void getAll() throws Exception {
        given(service.findAll()).willReturn(new LinkedList<>());
        mvc.perform(get("/genres")).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        given(service.findById(1L)).willReturn(getTestGenreWithId());
        mvc.perform(get("/genres/1")).andExpect(status().isOk());
    }

    @Test
    void add() throws Exception {
        given(
                service.insert(
                        getTestGenre()
                )
        ).willReturn(
                getTestGenreWithId()
        );
        mvc.perform(
                post("/genres")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(
                                        getTestGenre()
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        given(
                service.update(
                        getTestGenreWithId()
                )
        ).willReturn(
                getTestGenreWithId()
        );
        mvc.perform(
                put("/genres/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(
                                        getTestGenreWithId()
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/genres/1")
        ).andExpect(status().isOk());
    }

    private Genre getTestGenre() {
        return new Genre(
                "test"
        );
    }

    private Genre getTestGenreWithId() {
        return new Genre(
                1L,
                "test"
        );
    }
}