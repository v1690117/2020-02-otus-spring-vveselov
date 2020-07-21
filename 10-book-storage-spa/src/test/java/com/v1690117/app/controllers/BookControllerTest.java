package com.v1690117.app.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.v1690117.app.BookDto;
import com.v1690117.app.model.Book;
import com.v1690117.app.services.BookService;
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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService service;

    @Test
    void getAll() throws Exception {
        given(service.findAll()).willReturn(Collections.singletonList(getTestBookWithId()));
        mvc.perform(get("/books")).andExpect(status().isOk());
    }

    @Test
    void getById() throws Exception {
        given(service.findById(1L)).willReturn(getTestBookWithId());
        mvc.perform(get("/books/1")).andExpect(status().isOk());
    }

    @Test
    void add() throws Exception {
        given(
                service.insert(
                        getTestBookDto()
                )
        ).willReturn(
                getTestBookWithId()
        );
        mvc.perform(
                post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(
                                        getTestBookMap()
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void update() throws Exception {
        given(
                service.update(
                        getTestBookDtoWithId()
                )
        ).willReturn(
                getTestBookWithId()
        );
        mvc.perform(
                put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                new ObjectMapper().writeValueAsString(
                                        getTestBookMapWithId()
                                )
                        )
        ).andExpect(status().isOk());
    }

    @Test
    void delete() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders.delete("/books/1")
        ).andExpect(status().isOk());
    }

    private Book getTestBook() {
        return new Book(
                "title",
                "annotation",
                "year",
                new LinkedList<>(),
                new LinkedList<>()
        );
    }

    private Book getTestBookWithId() {
        return new Book(
                1L,
                "title",
                "annotation",
                "year",
                new LinkedList<>(),
                new LinkedList<>(),
                new LinkedList<>()
        );
    }

    private Map getTestBookMap() {
        Map<String, Object> book = new HashMap<>();
        book.put("title", "title");
        book.put("annotation", "annotation");
        book.put("year", "year");
        return book;
    }

    private BookDto getTestBookDto() {
        return new BookDto(getTestBook());
    }

    private BookDto getTestBookDtoWithId() {
        return new BookDto(getTestBookWithId());
    }

    private Map getTestBookMapWithId() {
        Map<String, Object> book = new HashMap<>();
        book.put("id", "1");
        book.put("title", "title");
        book.put("annotation", "annotation");
        book.put("year", "year");
        return book;
    }
}