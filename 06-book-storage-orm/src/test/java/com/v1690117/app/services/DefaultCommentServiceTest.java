package com.v1690117.app.services;

import com.v1690117.app.Application;
import com.v1690117.app.dao.BookDao;
import com.v1690117.app.dao.CommentDao;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("Comment service")
@SpringBootTest(classes = Application.class)
class DefaultCommentServiceTest {
    @Autowired
    private CommentService commentService;

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private BookDao bookDao;

    @Test
    void findCommentsForBook() {
        List<Comment> expected = Collections.singletonList(
                new Comment(1, "test comment", new Book())
        );
        given(commentDao.findCommentsForBook(1L)).willReturn(expected);
        assertThat(commentService.findCommentsForBook(1L)).isNotNull()
                .isEqualTo(expected);
    }

    @Test
    void addCommentForBook() {
        commentService.addCommentForBook(1L, "test comment");
        given(bookDao.findById(1L)).willReturn(new Book());
        verify(commentDao, times(1)).addComment(any());
    }
}