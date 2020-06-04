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
public class BookRepository implements BookDao {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public Book findById(long id) {
        Book book = manager.find(Book.class, id);
        return book;
    }

    @Override
    public List<Book> findAll() {
        return manager.createQuery(
                "select b from Book b",
                Book.class
        ).getResultList();
    }

    @Override
    @Transactional
    public Book insert(Book book) {
        manager.persist(book);
        return book;
    }

    @Override
    @Transactional
    public void update(Book book) {
        manager.merge(book);
    }

    @Override
    @Transactional
    public void delete(long id) {
        manager.remove(
                findById(id)
        );
    }
}
