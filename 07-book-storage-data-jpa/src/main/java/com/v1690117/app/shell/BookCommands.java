package com.v1690117.app.shell;

import com.v1690117.app.model.Book;
import com.v1690117.app.services.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookService bookService;

    @ShellMethod(value = "Prints book(s)", key = {"b", "books"})
    public void getAll(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            bookService.findAll().forEach(System.out::println);
        else
            System.out.println(bookService.findById(id));
    }

    @ShellMethod(value = "Adds book", key = {"ab", "add book"})
    public void add(
            @ShellOption(value = {"-t", "--title"}) String title,
            @ShellOption(defaultValue = "", value = {"--annotation"}) String annotation,
            @ShellOption(defaultValue = "", value = {"-y", "--year"}) String year,
            @ShellOption(defaultValue = "", value = {"--authors"}) long[] authors,
            @ShellOption(defaultValue = "", value = {"-g", "--genres"}) long[] genres
    ) {
        Book book = bookService.insert(
                title,
                annotation,
                year,
                authors,
                genres
        );
        System.out.println(
                String.format(
                        "Book was added:\n%s",
                        book
                )
        );
    }

    @ShellMethod(value = "Updates the book", key = {"ub", "update book"})
    public void update(
            @ShellOption(value = {"-i", "--id"}) long id,
            @ShellOption(defaultValue = "", value = {"-t", "--title"}) String title,
            @ShellOption(defaultValue = "", value = {"-a", "--annotation"}) String annotation,
            @ShellOption(defaultValue = "", value = {"-y", "--year"}) String year,
            @ShellOption(defaultValue = "", value = {"--authors"}) long[] authors,
            @ShellOption(defaultValue = "", value = {"-g", "--genres"}) long[] genres,
            @ShellOption(defaultValue = "", value = {"-c", "--comment"}) String comment
    ) {
        Book updated = bookService.update(
                id,
                title,
                annotation,
                year,
                authors,
                genres,
                comment
        );
        System.out.println(
                String.format(
                        "Book was updated:\n%s",
                        updated
                )
        );
    }

    @ShellMethod(value = "Clears storage", key = {"cb", "clear books"})
    public void clear() {
        bookService.findAll().forEach(book -> bookService.delete(book.getId()));
    }

    @ShellMethod(value = "Deletes the book", key = {"db", "delete book"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        bookService.delete(id);
    }
}
