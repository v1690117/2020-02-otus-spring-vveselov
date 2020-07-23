package com.v1690117.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1690117.app.model.Author;
import com.v1690117.app.services.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
class AuthorControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private AuthorService service;

    @Test
    void getAll() throws Exception {
        given(service.findAll()).willReturn(Collections.singletonList(getTestAuthor()));
        mvc.perform(get("/authors")).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        given(service.findById(1L)).willReturn(getTestAuthorWithId());
        mvc.perform(get("/authors/1")).andExpect(status().isOk());
    }

    @Test
    void add() throws Exception {
        given(
                service.insert(
                        getTestAuthor()
                )
        ).willReturn(
                getTestAuthorWithId()
        );
        mvc.perform(
                post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(
                                        getTestAuthor()
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        given(
                service.update(
                        getTestAuthorWithId()
                )
        ).willReturn(
                getTestAuthorWithId()
        );
        mvc.perform(
                put("/authors/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(
                                        getTestAuthorWithId()
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/authors/1")
        ).andExpect(status().isOk());
    }

    private Author getTestAuthor() {
        return new Author(
                "First",
                "Last"
        );
    }

    private Author getTestAuthorWithId() {
        return new Author(
                1L,
                "First",
                "Last"
        );
    }
}