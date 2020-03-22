package com.github.v1690117.app.poll.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PollAnswerTest {

    @DisplayName("Answer is validated correctly")
    @Test
    public void isCorrectTest() {
        assertEquals(
                new PollAnswer(
                        new Question.FakeQuestion(true), "correct")
                        .isCorrect(),
                true
        );
    }

    @DisplayName("Provided value for answer is retrieved")
    @Test
    public void valueTest() {
        assertEquals(
                "correct",
                new PollAnswer(
                        new Question.FakeQuestion(true),
                        "correct"
                ).value()
        );
    }
}