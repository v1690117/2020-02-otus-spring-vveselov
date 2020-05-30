package com.v1690117.app.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Book")
class BookTest {

    @DisplayName("Just to be sure it do something")
    @Test
    void testToString() {
        assertThat(
                new Book(
                        1,
                        "title",
                        "annotation",
                        "2020",
                        Collections.emptyList(),
                        Collections.emptyList()
                ).toString()
        ).isNotBlank();
    }
}