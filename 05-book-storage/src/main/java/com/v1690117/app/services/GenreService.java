package com.v1690117.app.services;

import com.v1690117.app.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre findById(long id);

    void insert(Genre genre);

    void update(Genre genre);

    void delete(long id);
}
