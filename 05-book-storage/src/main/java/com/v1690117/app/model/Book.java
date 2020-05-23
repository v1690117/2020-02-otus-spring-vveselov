package com.v1690117.app.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class Book {
    private final long id;
    private final String title;
    private final String annotation;
    private final String year;
    private final List<Author> authors;
    private final List<Genre> genres;

    public Map<String, Object> map() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("title", title);
            put("annotation", annotation);
            put("year ", year);
            put("authors", authors);
            put("genres", genres);
        }};
    }

    @Override
    public String toString() {
        return String.format(
                "%s. '%s' (%s) by %s.\n%s.\n%s\n",
                id,
                title,
                year,
                authors.stream()
                        .map(Author::toString)
                        .collect(Collectors.joining(", ")),
                genres.stream()
                        .map(Genre::toString)
                        .collect(Collectors.joining(", ")),
                annotation
        );
    }
}
