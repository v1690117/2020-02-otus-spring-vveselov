package com.v1690117.app.services;

import com.v1690117.app.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> findAll();

    Author findById(long id);

    Author insert(Author author);

    Author update(Author author);

    void delete(long id);
}
