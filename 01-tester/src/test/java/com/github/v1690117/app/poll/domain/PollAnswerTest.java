package com.github.v1690117.app.poll.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PollAnswerTest {

    @Test
    public void isCorrectTest() {
        assertEquals("Answer is validated correctly",
                new PollAnswer(
                        new Question.FakeQuestion(true), "correct")
                        .isCorrect(),
                true
        );
    }

    @Test
    public void valueTest() {
        assertEquals("Provided value for answer is retrieved",
                "correct",
                new PollAnswer(
                        new Question.FakeQuestion(true),
                        "correct"
                ).value()
        );
    }
}