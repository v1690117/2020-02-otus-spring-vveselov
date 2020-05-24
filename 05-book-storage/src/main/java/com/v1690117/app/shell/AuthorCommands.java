package com.v1690117.app.shell;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final AuthorDao authorDao;

    @ShellMethod(value = "Prints author(s)", key = {"a", "authors"})
    public void getAll(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            authorDao.findAll().forEach(System.out::println);
        else
            System.out.println(authorDao.findById(id));
    }

    @ShellMethod(value = "Adds author", key = {"aa", "add author"})
    public void add(
            @ShellOption(value = {"-f", "--firstname"}, defaultValue = "") String firstName,
            @ShellOption(value = {"-l", "--lastname"}) String lastName
    ) {
        if (lastName == null || lastName.trim().isEmpty())
            throw new IllegalArgumentException("Last name can not be empty!");
        authorDao.insert(
                new Author(
                        authorDao.count() + 1,
                        firstName,
                        lastName
                )
        );
    }

    @ShellMethod(value = "Updates the author", key = {"ua", "update author"})
    public void update(
            @ShellOption(value = {"-i", "--id"}) long id,
            @ShellOption(value = {"-f", "--firstname"}) String firstName,
            @ShellOption(value = {"-l", "--lastname"}) String lastName
    ) {
        Author author = authorDao.findById(id);
        if (firstName == null)
            firstName = author.getFirstName();
        if (lastName == null)
            lastName = author.getLastName();
        authorDao.update(
                new Author(
                        id,
                        firstName,
                        lastName
                )
        );
    }

    @ShellMethod(value = "Clears authors storage", key = {"ca", "clear authors"})
    public void clear() {
        authorDao.findAll().forEach(genre -> authorDao.delete(genre.getId()));
    }

    @ShellMethod(value = "Deletes the author", key = {"da", "delete author"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        authorDao.delete(id);
    }
}
