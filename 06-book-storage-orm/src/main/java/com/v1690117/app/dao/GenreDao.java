package com.v1690117.app.dao;

import com.v1690117.app.model.Genre;

import java.util.List;

public interface GenreDao {
    Genre findById(long id);

    List<Genre> findAll();

    Genre insert(Genre genre);

    void update(Genre genre);

    void delete(long id);
}
