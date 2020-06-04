package com.v1690117.app.services;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Comment;
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
        Book book = bookDao.findById(id);
        book.getComments();
        return book;
    }

    @Override
    public Book insert(String title, String annotation, String year, long[] authors, long[] genres) {
        if (title.trim().isEmpty())
            throw new IllegalArgumentException("Title can not be empty!");
        List<Author> authorList = new LinkedList<>();
        List<Genre> genreList = new LinkedList<>();
        for (long authorId : authors) {
            authorList.add(authorDao.findById(authorId));
        }
        for (long genreId : genres) {
            genreList.add(genreDao.findById(genreId));
        }
        return bookDao.insert(
                new Book(
                        title,
                        annotation,
                        year,
                        authorList,
                        genreList
                )
        );
    }

    @Override
    public Book update(long id, String title, String annotation, String year, long[] authors, long[] genres, String comment) {
        Book book = bookDao.findById(id);
        if (title != null && !title.isEmpty())
            book.setTitle(title);
        if (annotation != null && !annotation.isEmpty())
            book.setAnnotation(annotation);
        if (year != null && !year.isEmpty())
            book.setYear(year);
        if (authors != null && authors.length > 0) {
            List<Author> newAuthors = new LinkedList<>();
            for (long authorId : authors) {
                newAuthors.add(authorDao.findById(authorId));
            }
            book.setAuthors(newAuthors);
        }
        if (genres != null && genres.length > 0) {
            List<Genre> newGenres = new LinkedList<>();
            for (long genreId : genres) {
                newGenres.add(genreDao.findById(genreId));
            }
            book.setGenres(newGenres);
        }
        if (comment != null && !comment.trim().isEmpty()) {
            Comment cmt = new Comment(comment);
            cmt.setBook(book);
            book.getComments().add(cmt);
        }
        bookDao.update(book);
        return book;
    }

    @Override
    public void delete(long id) {
        bookDao.delete(id);
    }
}
