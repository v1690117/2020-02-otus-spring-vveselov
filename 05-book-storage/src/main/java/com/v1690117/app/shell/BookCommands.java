package com.v1690117.app.shell;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.LinkedList;
import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class BookCommands {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @ShellMethod(value = "Prints book(s)", key = {"b", "books"})
    public void getAll(@ShellOption(value = {"-i", "--id"}, defaultValue = "-1") long id) {
        if (id == -1)
            bookDao.findAll().forEach(System.out::println);
        else
            System.out.println(bookDao.findById(id));
    }

    @ShellMethod(value = "Adds book", key = {"ab", "add book"})
    public void add(
            @ShellOption(value = {"-t", "--title"}) String title,
            @ShellOption(defaultValue = "", value = {"--annotation"}) String annotation,
            @ShellOption(defaultValue = "", value = {"-y", "--year"}) String year,
            @ShellOption(defaultValue = "", value = {"--authors"}) long[] authors,
            @ShellOption(defaultValue = "", value = {"-g", "--genres"}) long[] genres
    ) {
        if (title.trim().isEmpty())
            throw new IllegalArgumentException("Title can not be empty!");
        List<Author> authorList = new LinkedList<>();
        List<Genre> genreList = new LinkedList<>();
        for (long authorId : authors) {
            authorList.add(
                    authorDao.findById(authorId)
            );
        }
        for (long genreId : genres) {
            genreList.add(
                    genreDao.findById(genreId)
            );
        }
        long bookId = bookDao.count() + 1;
        bookDao.insert(
                new Book(
                        bookId,
                        title,
                        annotation,
                        year,
                        authorList,
                        genreList
                )
        );
        System.out.println(
                String.format(
                        "Book was added:\n%s",
                        bookDao.findById(bookId)
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
            @ShellOption(defaultValue = "", value = {"-g", "--genres"}) long[] genres
    ) {
        Book book = bookDao.findById(id);
        if (title.isEmpty())
            title = book.getTitle();
        if (annotation.isEmpty())
            annotation = book.getAnnotation();
        if (year.isEmpty())
            year = book.getYear();
        List<Author> authorList = new LinkedList<>();
        List<Genre> genreList = new LinkedList<>();
        if (authors.length > 0) {
            for (long authorId : authors) {
                authorList.add(
                        authorDao.findById(authorId)
                );
            }
        } else {
            authorList.addAll(book.getAuthors());
        }
        if (genres.length > 0) {
            for (long genreId : genres) {
                genreList.add(
                        genreDao.findById(genreId)
                );
            }
        } else {
            genreList.addAll(book.getGenres());
        }
        bookDao.update(
                new Book(
                        id,
                        title,
                        annotation,
                        year,
                        authorList,
                        genreList
                )
        );
        System.out.println(
                String.format(
                        "Book was updated:\n%s",
                        bookDao.findById(id)
                )
        );
    }

    @ShellMethod(value = "Clears storage", key = {"cb", "clear books"})
    public void clear() {
        bookDao.findAll().forEach(book -> bookDao.delete(book.getId()));
    }

    @ShellMethod(value = "Deletes the book", key = {"db", "delete book"})
    public void delete(@ShellOption(value = {"-i", "--id"}) long id) {
        bookDao.delete(id);
    }
}
