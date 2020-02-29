package com.github.v1690117.app.poll.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class VariantsTest {

    @Test
    public void testToString() {
        assertEquals("Variants are displayed correctly",
                "a\nb\nc",
                new Variants("a|b|c").toString()
        );
    }
}