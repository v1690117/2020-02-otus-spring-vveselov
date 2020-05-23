package com.v1690117.app.shell;

import com.v1690117.app.dao.BookDao;
import com.v1690117.app.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;

@ShellComponent
@RequiredArgsConstructor
public class Commands {
    private final BookDao dao;

    @ShellMethod(value = "Prints book(s)", key = {"p", "print"})
    public void get(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            dao.findAll().forEach(System.out::println);
        else
            System.out.println(dao.findById(id));
    }

    @ShellMethod(value = "Adds book", key = {"a", "add"})
    public void add(@ShellOption(value = {"-t", "--title"}) String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title can not be empty!");
        dao.insert(
                new Book(
                        dao.count() + 1,
                        title,
                        "annotation",
                        "year", // todo
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
    }

    @ShellMethod(value = "Updates the book", key = {"u", "update"})
    public void update(
            @ShellOption(value = {"-i", "--id"}) long id,
            @ShellOption(value = {"-t", "--title"}) String title
    ) {
        Book book = dao.findById(id);
        if (title == null)
            title = book.getTitle();
        dao.update(
                new Book(
                        id,
                        title,
                        "annotation",
                        "year", // todo
                        new ArrayList<>(),
                        new ArrayList<>()
                )
        );
    }

    @ShellMethod(value = "Clears storage", key = {"c", "clean"})
    public void clear() {
        dao.findAll().forEach(book -> dao.delete(book.getId()));
    }

    @ShellMethod(value = "Deletes the book", key = {"d", "delete"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        dao.delete(id);
    }
}
