package com.v1690117.app.dao;

import com.v1690117.app.model.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> findCommentsForBook(long bookId);

    void addComment(Comment comment);
}
