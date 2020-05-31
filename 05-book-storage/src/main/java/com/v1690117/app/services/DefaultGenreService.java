package com.v1690117.app.services;

import com.v1690117.app.dao.GenreDao;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {
    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        return genreDao.findAll();
    }

    @Override
    public Genre findById(long id) {
        return genreDao.findById(id);
    }

    @Override
    public void insert(Genre genre) {
        if (genre.getName() == null || genre.getName().trim().isEmpty())
            throw new IllegalArgumentException("Name of the genre can not be empty!");
        genreDao.insert(
                new Genre(
                        genreDao.count() + 1,
                        genre.getName()
                )
        );
    }

    @Override
    public void update(Genre given) {
        Genre existed = genreDao.findById(given.getId());
        String name = (given.getName() == null || given.getName().trim().isEmpty()) ?
                existed.getName() : given.getName();
        genreDao.update(
                new Genre(
                        given.getId(),
                        name
                )
        );
    }

    @Override
    public void delete(long id) {
        genreDao.delete(id);
    }
}
