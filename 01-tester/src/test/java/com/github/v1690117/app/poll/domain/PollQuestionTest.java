package com.github.v1690117.app.poll.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class PollQuestionTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private Question question;

    @Before
    public void init() {
        question = new PollQuestion("1", "Are you correct?", "correct|incorrect", "correct");
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testValidate() {
        Assert.assertEquals("",
                true,
                question.validate(new Answer.FakeRightAnswer())
        );
        Assert.assertEquals("",
                false,
                question.validate(new Answer.FakeWrongAnswer())
        );
    }

    @Test
    public void testAsk() {
        question.ask();
        Assert.assertEquals("Ask method works",
                removeEndLineChars("1) Are you correct?\ncorrect\nincorrect\n"),
                removeEndLineChars(outContent.toString())
        );
    }

    @Test
    public void testToString() {
        Assert.assertEquals("String value is correct",
                "1) Are you correct?\ncorrect\nincorrect",
                question.toString()
        );
    }

    private String removeEndLineChars(String in) {
        return in.replaceAll("\\n", "").replaceAll("\\r", "");
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
}