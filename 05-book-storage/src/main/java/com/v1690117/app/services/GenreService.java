package com.v1690117.app.services;

import com.v1690117.app.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> findAll();

    Genre findById(long id);

    void insert(Genre author);

    void update(Genre author);

    void delete(long id);
}
