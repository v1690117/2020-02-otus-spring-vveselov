package com.github.v1690117.app.poll.domain;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class VariantsTest {

    @DisplayName("Variants are displayed correctly")
    @Test
    public void testToString() {
        assertEquals(
                "a\nb\nc",
                new Variants("a|b|c").toString()
        );
    }
}