package com.v1690117.app.services;

import com.v1690117.app.BookDto;
import com.v1690117.app.model.Book;

import java.util.List;

public interface BookService {

    List<Book> findAll();

    Book findById(long id);

    Book insert(BookDto book);

    Book update(BookDto bookDto);

    void delete(long id);
}
