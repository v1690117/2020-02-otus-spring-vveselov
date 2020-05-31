package com.v1690117.app.dao;

import com.v1690117.app.model.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
@Profile("hibernate")
public class GenreRepository implements GenreDao {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public long count() {
        return manager.createQuery(
                "select count(g) from Genre g",
                Long.class
        ).getSingleResult();
    }

    @Override
    public Genre findById(long id) {
        return manager.find(Genre.class, id);
    }

    @Override
    public List<Genre> findAll() {
        return manager.createQuery(
                "select g from Genre g",
                Genre.class
        ).getResultList();
    }

    @Override
    public void insert(Genre genre) {
        manager.persist(genre);
    }

    @Override
    public void update(Genre genre) {
        manager.merge(genre);
    }

    @Override
    public void delete(long id) {
        manager.remove(findById(id));
    }
}
