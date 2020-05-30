package com.v1690117.app.dao;

import com.v1690117.app.model.Genre;

import java.util.List;

public interface BookGenresDao {
    long count();

    List<Genre> findGenresByBookId(long id);

    void addGenreForBook(long bookId, long genreId);

    void deleteGenreForBook(long bookId, long genreId);
}
