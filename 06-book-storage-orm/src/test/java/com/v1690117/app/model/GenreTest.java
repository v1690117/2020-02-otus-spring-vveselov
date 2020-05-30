package com.v1690117.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genre")
class GenreTest {

    @DisplayName("Just to be sure it do something")
    @Test
    void testToString() {
        assertThat(
                new Genre(
                        1,
                        "Testing"
                ).toString()
        ).isNotBlank();
    }
}