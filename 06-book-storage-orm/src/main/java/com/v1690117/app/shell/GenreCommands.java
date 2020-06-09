package com.v1690117.app.shell;

import com.v1690117.app.model.Genre;
import com.v1690117.app.services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {
    private final GenreService genreService;

    @ShellMethod(value = "Prints genre(s)", key = {"g", "genres"})
    public void getAll(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            genreService.findAll().forEach(System.out::println);
        else
            System.out.println(genreService.findById(id));
    }

    @ShellMethod(value = "Adds genre", key = {"ag", "add genre"})
    public void add(@ShellOption(value = {"-n", "--name"}) String name) {
        genreService.insert(
                new Genre(
                        name
                )
        );
    }

    @ShellMethod(value = "Updates the genre", key = {"ug", "update genre"})
    public void update(
            @ShellOption(value = {"-i", "--id"}) long id,
            @ShellOption(value = {"-n", "--name"}) String name
    ) {
        genreService.update(
                new Genre(
                        id,
                        name
                )
        );
    }

    @ShellMethod(value = "Clears genres storage", key = {"cg", "clear genres"})
    public void clear() {
        genreService.findAll().forEach(genre -> genreService.delete(genre.getId()));
    }

    @ShellMethod(value = "Deletes the genre", key = {"dg", "delete genre"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        genreService.delete(id);
    }
}
