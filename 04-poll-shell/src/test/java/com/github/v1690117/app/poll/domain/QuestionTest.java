package com.github.v1690117.app.poll.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {

    @Test
    public void testAsk() {
        new Question.FakeQuestion(true).ask();
    }

    @Test
    public void testValidate() {
        assertEquals(
                true,
                new Question.FakeQuestion(true).validate(new Answer.FakeRightAnswer())
        );
        assertEquals(
                false,
                new Question.FakeQuestion(false).validate(new Answer.FakeWrongAnswer())
        );
    }
}