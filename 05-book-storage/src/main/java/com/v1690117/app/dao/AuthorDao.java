package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import com.v1690117.app.model.Genre;

import java.util.List;

public interface AuthorDao {
    long count();

    Author findById(long id);

    List<Author> findAll();

    void insert(Author author);

    void update(Author author);

    void delete(long id);
}
