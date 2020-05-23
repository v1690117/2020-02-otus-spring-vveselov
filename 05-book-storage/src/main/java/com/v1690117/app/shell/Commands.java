package com.v1690117.app.shell;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.ArrayList;

@ShellComponent
@RequiredArgsConstructor
public class Commands {
    private final BookDao bookDao;
    private final GenreDao genreDao;
    private final AuthorDao authorDao;

    @ShellMethod(value = "Prints book(s)", key = {"b", "books"})
    public void getBooks(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            bookDao.findAll().forEach(System.out::println);
        else
            System.out.println(bookDao.findById(id));
    }

    @ShellMethod(value = "Prints genre(s)", key = {"g", "genres"})
    public void getGenres(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            genreDao.findAll().forEach(System.out::println);
        else
            System.out.println(genreDao.findById(id));
    }

    @ShellMethod(value = "Prints author(s)", key = {"a", "authors"})
    public void getAuthors(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            authorDao.findAll().forEach(System.out::println);
        else
            System.out.println(authorDao.findById(id));
    }

    @ShellMethod(value = "Adds book", key = {"add"})
    public void add(@ShellOption(value = {"-t", "--title"}) String title) {
        if (title == null || title.trim().isEmpty())
            throw new IllegalArgumentException("Title can not be empty!");
        bookDao.insert(
                new Book(
                        bookDao.count() + 1,
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
        Book book = bookDao.findById(id);
        if (title == null)
            title = book.getTitle();
        bookDao.update(
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
        bookDao.findAll().forEach(book -> bookDao.delete(book.getId()));
    }

    @ShellMethod(value = "Deletes the book", key = {"d", "delete"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        bookDao.delete(id);
    }
}
