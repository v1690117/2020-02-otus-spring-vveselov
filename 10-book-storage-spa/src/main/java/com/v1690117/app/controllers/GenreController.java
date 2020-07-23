package com.v1690117.app.controllers;

import com.v1690117.app.model.Genre;
import com.v1690117.app.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping(value = "/genres")
    public List<Genre> getAll() {
        return genreService.findAll();
    }

    @GetMapping("/genres/{id}")
    public Genre getById(@PathVariable("id") long id) {
        return genreService.findById(id);
    }

    @PostMapping("/genres")
    public Genre add(@RequestBody Genre genre) {
        return genreService.insert(genre);
    }

    @PutMapping("/genres/{id}")
    public Genre update(@RequestBody Genre genre) {
        return genreService.update(
                genre
        );
    }

    @DeleteMapping("/genres/{id}")
    public void delete(@PathVariable("id") long id) {
        genreService.delete(id);
    }
}
