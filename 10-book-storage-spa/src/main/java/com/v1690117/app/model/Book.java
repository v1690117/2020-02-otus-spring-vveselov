package com.v1690117.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Collections;
import java.util.List;
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
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "annotation")
    private String annotation;

    @Column(name = "year")
    private String year;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "books_authors",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")}
    )
    private List<Author> authors;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "books_genres",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private List<Genre> genres;

    @Fetch(FetchMode.SUBSELECT)
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "book")
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

    @JsonIgnore
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
