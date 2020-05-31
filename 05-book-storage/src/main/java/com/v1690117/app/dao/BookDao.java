package com.v1690117.app.dao;

import com.v1690117.app.model.Book;

import java.util.List;

public interface BookDao {
    long count();

    Book findById(long id);

    List<Book> findAll();

    void insert(Book book);

    void update(Book book);

    void delete(long id);
}
