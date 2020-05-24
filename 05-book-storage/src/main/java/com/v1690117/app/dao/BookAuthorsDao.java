package com.v1690117.app.dao;

import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;

import java.util.List;

public interface BookAuthorsDao {
    List<Author> findAuthorsByBookId(long id);

    void addAuthorForBook(long bookId, long authorId);

    void deleteAuthorForBook(long bookId, long authorId);
}
