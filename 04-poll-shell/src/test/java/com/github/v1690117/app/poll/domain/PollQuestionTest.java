package com.github.v1690117.app.poll.domain;

import com.github.v1690117.test.util.NoEndLineText;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PollQuestionTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Question question;

    @BeforeEach
    public void init() {
        question = new PollQuestion("1", "Are you correct?", "correct|incorrect", "correct");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidate() {
        assertEquals(
                true,
                question.validate(new Answer.FakeRightAnswer())
        );
        assertEquals(
                false,
                question.validate(new Answer.FakeWrongAnswer())
        );
    }

    @DisplayName("Ask method works")
    @Test
    public void testAsk() {
        question.ask();
        assertEquals(
                new NoEndLineText("1) Are you correct?\ncorrect\nincorrect\n"),
                new NoEndLineText(outContent.toString())
        );
    }

    @DisplayName("String value is correct")
    @Test
    public void testToString() {
        assertEquals(
                "1) Are you correct?\ncorrect\nincorrect",
                question.toString()
        );
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}