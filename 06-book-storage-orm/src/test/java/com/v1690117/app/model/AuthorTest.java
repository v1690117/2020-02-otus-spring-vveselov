package com.v1690117.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Author")
class AuthorTest {

    @DisplayName("Just to be sure it do something")
    @Test
    void testToString() {
        assertThat(
                new Author(
                        1,
                        "Mister",
                        "Tester"
                ).toString()
        ).isNotBlank();
    }
}