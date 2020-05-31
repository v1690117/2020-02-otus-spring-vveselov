package com.v1690117.app.services;

import com.v1690117.app.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> findCommentsForBook(long id);

    void addCommentForBook(long bookId, String text);
}
