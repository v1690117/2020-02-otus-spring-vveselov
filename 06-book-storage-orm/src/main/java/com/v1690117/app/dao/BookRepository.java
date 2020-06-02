package com.v1690117.app.dao;

import com.v1690117.app.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class BookRepository implements BookDao {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public long count() {
        return manager.createQuery(
                "select count(b) from Book b",
                Long.class
        ).getSingleResult();
    }

    @Override
    public Book findById(long id) {
        return manager.find(Book.class, id);
    }

    @Override
    public List<Book> findAll() {
        return manager.createQuery(
                "select b from Book b",
                Book.class
        ).getResultList();
    }

    @Override
    public void insert(Book book) {
        manager.persist(book);
    }

    @Override
    public void update(Book book) {
        manager.merge(book);
    }

    @Override
    public void delete(long id) {
        manager.remove(
                findById(id)
        );
    }
}
