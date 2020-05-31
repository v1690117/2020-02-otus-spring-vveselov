package com.v1690117.app.services;

import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.CommentDao;
import com.v1690117.app.model.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultCommentService implements CommentService {
    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    public List<Comment> findCommentsForBook(long id) {
        return commentDao.findCommentsForBook(id);
    }

    @Override
    public void addCommentForBook(long bookId, String text) {
        Comment comment = new Comment();
        comment.setBook(bookDao.findById(bookId));
        comment.setText(text);
        commentDao.addComment(comment);
    }
}
