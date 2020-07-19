package com.v1690117.app.controllers;

import com.v1690117.app.model.Author;
import com.v1690117.app.services.AuthorService;
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
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping(value = "/authors.json")
    public List<Author> getAll() {
        return authorService.findAll();
    }

    @GetMapping("/authors/{id}.json")
    public Author getById(@PathVariable("id") long id) {
        return authorService.findById(id);
    }

    @PostMapping("/authors")
    public Author add(@RequestBody Author author) {
        return authorService.insert(author);
    }

    @PutMapping("/authors/{id}")
    public Author update(@RequestBody Author author) {
        return authorService.update(
                author
        );
    }

    @DeleteMapping("/authors/{id}")
    public void delete(@PathVariable("id") long id) {
        authorService.delete(id);
    }
}
