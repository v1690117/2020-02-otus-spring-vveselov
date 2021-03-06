package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AuthorRepository implements AuthorDao {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public Author findById(long id) {
        return manager.find(Author.class, id);
    }

    @Override
    public List<Author> findAll() {
        return manager.createQuery(
                "select a from Author a",
                Author.class
        ).getResultList();
    }

    @Override
    @Transactional
    public Author insert(Author author) {
        manager.persist(author);
        return author;
    }

    @Override
    @Transactional
    public void update(Author author) {
        manager.merge(author);
    }

    @Override
    @Transactional
    public void delete(long id) {
        manager.remove(findById(id));
    }
}
