package com.github.v1690117.app.poll.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AnswerTest {

    @Test
    public void testIsCorrect() {
        assertEquals(
                true,
                new Answer.FakeRightAnswer().isCorrect()
        );
        assertEquals(
                false,
                new Answer.FakeWrongAnswer().isCorrect()
        );
    }

    @Test
    public void value() {
        assertEquals(
                "correct",
                new Answer.FakeRightAnswer().value()
        );
        assertEquals(
                "incorrect",
                new Answer.FakeWrongAnswer().value()
        );
    }
}