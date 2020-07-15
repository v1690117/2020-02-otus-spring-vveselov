package com.v1690117.app.services;

import com.v1690117.app.BookDto;
import com.v1690117.app.dao.AuthorRepository;
import com.v1690117.app.dao.BookRepository;
import com.v1690117.app.dao.GenreRepository;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Comment;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DefaultBookService implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;

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
                        title,
                        annotation,
                        year,
                        authorList,
                        genreList
                )
        );
    }

    @Override
    @Transactional
    public Book update(BookDto bookDto) {
        Book book = bookRepository.findById(bookDto.getId()).get();
        if (bookDto.getTitle() != null && !bookDto.getTitle().isEmpty())
            book.setTitle(bookDto.getTitle());
        if (bookDto.getAnnotation() != null && !bookDto.getAnnotation().isEmpty())
            book.setAnnotation(bookDto.getAnnotation());
        if (bookDto.getYear() != null && !bookDto.getYear().isEmpty())
            book.setYear(bookDto.getYear());
        if (bookDto.getAuthors() != null && bookDto.getAuthors().size() > 0) {
            List<Author> newAuthors = new LinkedList<>();
            for (long authorId : bookDto.getAuthors()) {
                newAuthors.add(authorRepository.findById(authorId).get());
            }
            book.setAuthors(newAuthors);
        }
        if (bookDto.getGenres() != null && bookDto.getGenres().size() > 0) {
            List<Genre> newGenres = new LinkedList<>();
            for (long genreId : bookDto.getGenres()) {
                newGenres.add(genreRepository.findById(genreId).get());
            }
            book.setGenres(newGenres);
        }

        if (bookDto.getComments() != null) {
            Set<String> existingComments = book.getComments().stream().map(c -> c.getText()).collect(Collectors.toSet());
            for (String comment : bookDto.getComments()) {
                if (!comment.trim().isEmpty() && !existingComments.contains(comment)) {
                    Comment cmt = new Comment(comment);
                    cmt.setBook(book);
                    book.getComments().add(cmt);
                }
            }
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
