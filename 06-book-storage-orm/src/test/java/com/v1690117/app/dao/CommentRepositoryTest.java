package com.v1690117.app.dao;

import com.v1690117.app.model.Book;
import com.v1690117.app.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@Import(CommentRepository.class)
class CommentRepositoryTest {

    @Autowired
    private TestEntityManager manager;

    @Autowired
    private CommentDao dao;

    @Test
    void findCommentsForBook() {
        assertThat(dao.findCommentsForBook(1L)).isNotNull().hasSize(0);
        assertThat(dao.findCommentsForBook(2L)).isNotNull().hasSize(1);
    }

    @Test
    void addComment() {
        Comment comment = new Comment();
        comment.setBook(getFirstBook());
        comment.setText("Test comment");
        dao.addComment(comment);
        assertThat(manager.find(Comment.class, 3L))
                .isNotNull()
                .extracting(Comment::getText)
                .isEqualTo("Test comment");
    }

    private Book getFirstBook() {
        return new Book(
                1,
                "",
                "",
                "",
                Collections.emptyList(),
                Collections.emptyList()
        );
    }
}

