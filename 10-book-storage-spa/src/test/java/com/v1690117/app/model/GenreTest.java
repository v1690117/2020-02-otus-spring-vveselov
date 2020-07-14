package com.v1690117.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Genre")
class GenreTest {
    @DisplayName("Test constuctors")
    @Test
    void testConstructors() {
        assertThat(new Genre()).isNotNull();
        assertThat(new Genre(1L)).isNotNull();
        assertThat(new Genre("drama")).isNotNull();
    }

    @DisplayName("Just to be sure it do something")
    @Test
    void testToString() {
        assertThat(
                new Genre(
                        1L,
                        "Testing"
                ).toString()
        ).isNotBlank();
    }
}