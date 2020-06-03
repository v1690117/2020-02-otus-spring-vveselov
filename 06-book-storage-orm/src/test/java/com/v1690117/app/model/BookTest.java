package com.v1690117.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book")
class BookTest {
    @DisplayName("Test constuctors")
    @Test
    void testConstructors() {
        assertThat(new Book()).isNotNull();
        assertThat(
                new Book(
                        new Book(
                                1,
                                "title",
                                "annotation",
                                "2020",
                                Collections.emptyList(),
                                Collections.emptyList(),
                                Collections.emptyList())
                )
        ).isNotNull();
        assertThat(
                new Book(
                        "title",
                        "annotation",
                        "2020"
                )
        ).isNotNull();

    }

    @DisplayName("Just to be sure it do something")
    @Test
    void testToString() {
        assertThat(
                new Book(
                        "title",
                        "annotation",
                        "2020",
                        Collections.emptyList(),
                        Collections.emptyList()
                ).toString()
        ).isNotBlank();
    }
}