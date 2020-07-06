package com.v1690117.app;

import com.v1690117.app.model.Book;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BookDto {
    private Long id;
    private String title;
    private String annotation;
    private String year;
    private List<Long> authors;
    private List<Long> genres;
    private List<String> comments;

    public BookDto(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.annotation = book.getAnnotation();
        this.year = book.getYear();
        this.authors = book.getAuthors() == null ? Collections.emptyList()
                : book.getAuthors().stream().map(a -> a.getId()).collect(Collectors.toList());
        this.genres = book.getGenres() == null ? Collections.emptyList()
                : book.getGenres().stream().map(g -> g.getId()).collect(Collectors.toList());
        this.comments = book.getComments() == null ? Collections.emptyList()
                : book.getComments().stream().map(c -> c.getText()).collect(Collectors.toList());
    }
}
