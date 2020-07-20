package com.v1690117.app.controllers;

import com.v1690117.app.BookDto;
import com.v1690117.app.model.Book;
import com.v1690117.app.services.BookService;
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
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/books")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book getById(@PathVariable("id") long id) {
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public Book add(@RequestBody BookDto book) {
        return bookService.insert(
                book
        );
    }

    @PutMapping("/books/{id}")
    public Book update(@RequestBody BookDto book) {
        return bookService.update(book);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable("id") long id) {
        bookService.delete(id);
    }
}
