package com.v1690117.app.services;

import com.v1690117.app.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(long id);

    Book insert(String title, String annotation, String year, long[] authors, long[] genres);

    Book update(long id, String title, String annotation, String year, long[] authors, long[] genres, String comment);

    void delete(long id);
}
