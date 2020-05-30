package com.v1690117.app.services;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;

    @Override
    public List<Book> findAll() {
        return bookDao.findAll();
    }

    @Override
    public Book findById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public Book insert(String title, String annotation, String year, long[] authors, long[] genres) {
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
                        bookDao.count() + 1,
                        title,
                        annotation,
                        year,
                        authorList,
                        genreList
                )
        );
        return bookDao.findById(bookId);
    }

    @Override
    public Book update(long id, String title, String annotation, String year, long[] authors, long[] genres) {
        Book book = bookDao.findById(id);
        if (title == null || title.isEmpty())
            title = book.getTitle();
        if (annotation == null || annotation.isEmpty())
            annotation = book.getAnnotation();
        if (year == null || year.isEmpty())
            year = book.getYear();
        List<Author> authorList = new LinkedList<>();
        List<Genre> genreList = new LinkedList<>();
        if (authors != null && authors.length > 0) {
            for (long authorId : authors) {
                authorList.add(
                        authorDao.findById(authorId)
                );
            }
        } else {
            authorList.addAll(book.getAuthors());
        }
        if (genres != null && genres.length > 0) {
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
        return bookDao.findById(id);
    }

    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }
}
