package com.v1690117.app.controllers;

import com.v1690117.app.BookDto;
import com.v1690117.app.model.Book;
import com.v1690117.app.services.BookService;
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
public class BookController {
    private final BookService bookService;

    @GetMapping(value = "/books")
    public String getAll(Model model) {
        model.addAttribute("books", bookService.findAll());
        return "books";
    }

    @ResponseBody
    @GetMapping(value = "/books.json")
    public List<Book> getAll() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public String getById(@PathVariable("id") long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        return "book";
    }

    @ResponseBody
    @GetMapping("/books/{id}.json")
    public Book getById(@PathVariable("id") long id) {
        return bookService.findById(id);
    }

    @PostMapping("/books")
    public String add(
            @RequestParam("title") String title,
            @RequestParam("annotation") String annotation,
            @RequestParam("year") String year,
            @RequestParam("authors") long[] authors,
            @RequestParam("genres") long[] genres
    ) {
        bookService.insert(
                title,
                annotation,
                year,
                authors,
                genres
        );
        return "success";
    }

//    @PostMapping("/books/{id}")
//    public String update(BookDto book) {
//        bookService.update(
//                book.getId(),
//                book.getTitle(),
//                book.getAnnotation(),
//                book.getYear(),
//                book.getAuthors()
//        );
//        return "success";
//    }

    @DeleteMapping("/books/{id}")
    public String delete(@PathVariable("id") long id) {
        bookService.delete(id);
        return "deleted";
    }
}
