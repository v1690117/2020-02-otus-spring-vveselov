package com.v1690117.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document(collection = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Transient
    public static final String SEQUENCE_NAME = "books_sequence";

    @Id
    @Field(name = "book_id")
    private Long id;

    @Field(name = "title")
    private String title;

    @Field(name = "annotation")
    private String annotation;

    @Field(name = "year")
    private String year;

    private List<Author> authors;

    private List<Genre> genres;

    private List<Comment> comments;

    public Book(String title, String annotation, String year, List<Author> authors, List<Genre> genres) {
        this(
                null,
                title,
                annotation,
                year,
                authors,
                genres,
                Collections.emptyList()
        );
    }

    public Book(String title, String annotation, String year) {
        this(
                title,
                annotation,
                year,
                Collections.emptyList(),
                Collections.emptyList()
        );
    }

    public String getPrintableInfo() {
        return String.format(
                "%s-------------------\n%s\n",
                toString(),
                comments.stream()
                        .map(Comment::toString)
                        .collect(Collectors.joining("\n"))
        );
    }

    @Override
    public String toString() {
        return String.format(
                "%d. '%s' (%s y.) by %s.\n%s.\n%s\n",
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
