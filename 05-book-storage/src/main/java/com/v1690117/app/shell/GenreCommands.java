package com.v1690117.app.shell;

import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class GenreCommands {
    private final GenreDao genreDao;

    @ShellMethod(value = "Prints genre(s)", key = {"g", "genres"})
    public void getAll(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            genreDao.findAll().forEach(System.out::println);
        else
            System.out.println(genreDao.findById(id));
    }

    @ShellMethod(value = "Adds genre", key = {"ag", "add genre"})
    public void add(@ShellOption(value = {"-n", "--name"}) String name) {
        if (name == null || name.trim().isEmpty())
            throw new IllegalArgumentException("Name can not be empty!");
        genreDao.insert(
                new Genre(
                        genreDao.count() + 1,
                        name
                )
        );
    }

    @ShellMethod(value = "Updates the genre", key = {"ug", "update genre"})
    public void update(
            @ShellOption(value = {"-i", "--id"}) long id,
            @ShellOption(value = {"-n", "--name"}) String name
    ) {
        Genre genre = genreDao.findById(id);
        if (name == null)
            name = genre.getName();
        genreDao.update(
                new Genre(
                        id,
                        name
                )
        );
    }

    @ShellMethod(value = "Clears genres storage", key = {"cg", "clear genres"})
    public void clear() {
        genreDao.findAll().forEach(genre -> genreDao.delete(genre.getId()));
    }

    @ShellMethod(value = "Deletes the genre", key = {"dg", "delete genre"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        genreDao.delete(id);
    }
}
