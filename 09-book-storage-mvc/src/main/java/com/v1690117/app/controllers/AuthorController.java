package com.v1690117.app.controllers;

import com.v1690117.app.model.Author;
import com.v1690117.app.services.AuthorService;
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
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(value = "/authors")
    public String getAll(Model model) {
        model.addAttribute("authors", authorService.findAll());
        return "authors";
    }

    @ResponseBody
    @GetMapping(value = "/authors.json")
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "author";
    }

    @ResponseBody
    @GetMapping("/authors/{id}.json")
    public Author getById(@PathVariable("id") long id) {
        return authorService.findById(id);
    }

    @GetMapping("/authors/new")
    public String getCreationForm(Model model) {
        model.addAttribute("author", new Author());
        return "author";
    }

    @PostMapping("/authors")
    public String add(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName
    ) {
        authorService.insert(
                new Author(
                        firstName,
                        lastName
                )
        );
        return "success";
    }

    @PostMapping("/authors/{id}")
    public String update(Author author) {
        authorService.update(
                author
        );
        return "success";
    }

    @DeleteMapping("/authors/{id}")
    public String delete(@PathVariable("id") long id) {
        authorService.delete(id);
        return "success";
    }
}
