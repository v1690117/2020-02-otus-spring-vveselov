package com.v1690117.app.shell;

import com.v1690117.app.model.Author;
import com.v1690117.app.services.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class AuthorCommands {
    private final AuthorService authorService;

    @ShellMethod(value = "Prints author(s)", key = {"a", "authors"})
    public void getAll(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            authorService.findAll().forEach(System.out::println);
        else
            System.out.println(authorService.findById(id));
    }

    @ShellMethod(value = "Adds author", key = {"aa", "add author"})
    public void add(
            @ShellOption(value = {"-f", "--firstname"}, defaultValue = "") String firstName,
            @ShellOption(value = {"-l", "--lastname"}) String lastName
    ) {
        authorService.insert(
                new Author(
                        -1,
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
        authorService.update(
                new Author(
                        id,
                        firstName,
                        lastName
                )
        );
    }

    @ShellMethod(value = "Clears authors storage", key = {"ca", "clear authors"})
    public void clear() {
        authorService.findAll().forEach(genre -> authorService.delete(genre.getId()));
    }

    @ShellMethod(value = "Deletes the author", key = {"da", "delete author"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        authorService.delete(id);
    }
}
