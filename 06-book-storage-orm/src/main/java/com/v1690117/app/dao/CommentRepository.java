package com.v1690117.app.dao;

import com.v1690117.app.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class CommentRepository implements CommentDao {
    @PersistenceContext
    private final EntityManager manager;

    @Override
    public List<Comment> findCommentsForBook(long bookId) {
        TypedQuery<Comment> query = manager.createQuery(
                "select c from Comment c where c.book.id = : bookId",
                Comment.class
        );
        query.setParameter("bookId", bookId);
        return query.getResultList();
    }

    @Override
    public void addComment(Comment comment) {
        manager.persist(comment);
    }
}
