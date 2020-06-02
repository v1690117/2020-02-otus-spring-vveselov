package com.v1690117.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
public class Book {
    @Id
    @Column(name = "book_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "year")
    private String year;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(
            name = "books_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private List<Genre> genres;

    public Book(Book book) {
        this(
                book.getId(),
                book.getTitle(),
                book.getAnnotation(),
                book.getYear(),
                book.getAuthors(),
                book.getGenres()
        );
    }

    public Book(String title, String annotation, String year, List<Author> authors, List<Genre> genres) {
        this(
                0,
                title,
                annotation,
                year,
                authors,
                genres
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

    public Map<String, Object> map() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("title", title);
            put("annotation", annotation);
            put("year", year);
            put("authors", authors);
            put("genres", genres);
        }};
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
