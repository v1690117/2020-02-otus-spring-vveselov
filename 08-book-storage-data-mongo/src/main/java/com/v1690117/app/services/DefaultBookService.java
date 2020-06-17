package com.v1690117.app.services;

import com.v1690117.app.dao.AuthorRepository;
import com.v1690117.app.dao.BookRepository;
import com.v1690117.app.dao.CommentRepository;
import com.v1690117.app.dao.GenreRepository;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Comment;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final CommentRepository commentRepository;
    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Book findById(long id) {
        Book book = bookRepository.findById(id).get();
        book.getComments().size(); // lazy field initialization
        return book;
    }

    @Override
    public Book insert(String title, String annotation, String year, long[] authors, long[] genres) {
        if (title.trim().isEmpty())
            throw new IllegalArgumentException("Title can not be empty!");
        List<Author> authorList = new LinkedList<>();
        List<Genre> genreList = new LinkedList<>();
        for (long authorId : authors) {
            authorList.add(authorRepository.findById(authorId).get());
        }
        for (long genreId : genres) {
            genreList.add(genreRepository.findById(genreId).get());
        }
        return bookRepository.save(
                new Book(
                        sequenceGenerator.generateSequence(Book.SEQUENCE_NAME),
                        title,
                        annotation,
                        year,
                        authorList,
                        genreList,
                        Collections.emptyList()
                )
        );
    }

    @Override
    @Transactional
    public Book update(long id, String title, String annotation, String year, long[] authors, long[] genres, String comment) {
        Book book = bookRepository.findById(id).get();
        if (title != null && !title.isEmpty())
            book.setTitle(title);
        if (annotation != null && !annotation.isEmpty())
            book.setAnnotation(annotation);
        if (year != null && !year.isEmpty())
            book.setYear(year);
        if (authors != null && authors.length > 0) {
            List<Author> newAuthors = new LinkedList<>();
            for (long authorId : authors) {
                newAuthors.add(authorRepository.findById(authorId).get());
            }
            book.setAuthors(newAuthors);
        }
        if (genres != null && genres.length > 0) {
            List<Genre> newGenres = new LinkedList<>();
            for (long genreId : genres) {
                newGenres.add(genreRepository.findById(genreId).get());
            }
            book.setGenres(newGenres);
        }
        if (comment != null && !comment.trim().isEmpty()) {
            Comment cmt = new Comment(comment);
            cmt.setId(sequenceGenerator.generateSequence(Comment.SEQUENCE_NAME));
            commentRepository.save(cmt);
            book.getComments().add(cmt);
        }
        bookRepository.save(book);
        return book;
    }

    @Override
    public void delete(long id) {
        bookRepository.delete(
                bookRepository.findById(id).get()
        );
    }
}
