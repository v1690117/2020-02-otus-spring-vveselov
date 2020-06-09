package com.v1690117.app.dao;

import com.v1690117.app.model.Author;

import java.util.List;

public interface AuthorDao {
    Author findById(long id);

    List<Author> findAll();

    Author insert(Author author);

    void update(Author author);

    void delete(long id);
}
