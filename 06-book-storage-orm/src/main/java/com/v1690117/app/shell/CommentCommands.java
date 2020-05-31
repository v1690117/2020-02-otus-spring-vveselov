package com.v1690117.app.shell;

import com.v1690117.app.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class CommentCommands {
    private final CommentService commentService;

    @ShellMethod(value = "Prints comments for book", key = {"c", "comments"})
    public void getCommentsForBook(@ShellOption(value = {"-i", "--id"}) long bookId) {
        commentService.findCommentsForBook(bookId).forEach(System.out::println);
    }

    @ShellMethod(value = "Adds comment for book", key = {"ac", "add comment"})
    public void addCommentForBook(
            @ShellOption(value = {"-i", "--id"}) long bookId,
            @ShellOption(value = {"-c", "--comment"}) String text
    ) {
        commentService.addCommentForBook(bookId, text);
    }
}
