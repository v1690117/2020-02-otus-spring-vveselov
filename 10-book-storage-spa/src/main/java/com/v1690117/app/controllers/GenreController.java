package com.v1690117.app.controllers;

import com.v1690117.app.model.Genre;
import com.v1690117.app.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class GenreController {
    private final GenreService genreService;

    @GetMapping(value = "/genres")
    public String getAll(Model model) {
        model.addAttribute("genres", genreService.findAll());
        return "genres";
    }

    @ResponseBody
    @GetMapping(value = "/genres.json")
    public List<Genre> getAll() {
        return genreService.findAll();
    }

    @GetMapping("/genres/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        model.addAttribute("genre", genreService.findById(id));
        return "genre";
    }

    @ResponseBody
    @GetMapping("/genres/{id}.json")
    public Genre getById(@PathVariable("id") long id) {
        return genreService.findById(id);
    }

    @GetMapping("/genres/new")
    public String getCreationForm(Model model) {
        model.addAttribute("genre", new Genre());
        return "genre";
    }

    @PostMapping("/genres")
    public String add(@RequestParam("name") String name) {
        genreService.insert(
                new Genre(
                        name
                )
        );
        return "success";
    }

    @PostMapping("/genres/{id}")
    public String update(Genre genre) {
        genreService.update(
                genre
        );
        return "success";
    }

    @DeleteMapping("/genres/{id}")
    public String delete(@PathVariable("id") long id) {
        genreService.delete(id);
        return "success";
    }
}
