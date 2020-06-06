package com.v1690117.app.services;

import com.v1690117.app.dao.GenreRepository;
import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultGenreService implements GenreService {
    private final GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(long id) {
        return genreRepository.findById(id).get();
    }

    @Override
    public Genre insert(Genre genre) {
        if (genre.getName() == null || genre.getName().trim().isEmpty())
            throw new IllegalArgumentException("Name of the genre can not be empty!");
        return genreRepository.save(genre);
    }

    @Override
    public void update(Genre given) {
        Genre existed = genreRepository.findById(given.getId()).get();
        String name = (given.getName() == null || given.getName().trim().isEmpty()) ?
                existed.getName() : given.getName();
        genreRepository.save(
                new Genre(
                        given.getId(),
                        name
                )
        );
    }

    @Override
    public void delete(long id) {
        genreRepository.delete(
                genreRepository.findById(id).get()
        );
    }
}
