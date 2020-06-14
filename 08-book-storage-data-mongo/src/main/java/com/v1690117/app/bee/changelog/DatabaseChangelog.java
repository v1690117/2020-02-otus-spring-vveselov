package com.v1690117.app.bee.changelog;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.DatabaseSequence;
import com.v1690117.app.model.Genre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ChangeLog
@AllArgsConstructor
public class DatabaseChangelog {

    @SneakyThrows
    @ChangeSet(order = "001", id = "addSamples", author = "v1690117")
    public void insertSampleData(MongoTemplate mt) {
        SampleData samples = new ObjectMapper().readValue(
                new InputStreamReader(
                        this.getClass()
                                .getClassLoader()
                                .getResourceAsStream("data.json"), // todo move to app props
                        StandardCharsets.UTF_8
                ),
                SampleData.class
        );
        samples.getGenres().forEach(genre -> mt.save(genre));
        samples.getAuthors().forEach(author -> mt.save(author));
        samples.getBooks().forEach(book -> mt.save(book));
        mt.save(
                new DatabaseSequence(
                        Book.SEQUENCE_NAME,
                        samples.getBooks().size()
                )
        );
        mt.save(
                new DatabaseSequence(
                        Genre.SEQUENCE_NAME,
                        samples.getGenres().size()
                )
        );
        mt.save(
                new DatabaseSequence(
                        Author.SEQUENCE_NAME,
                        samples.getAuthors().size()
                )
        );
    }

    @Getter
    @Setter
    @NoArgsConstructor
    static class SampleData {
        private List<Genre> genres;
        private List<Author> authors;
        private List<Book> books;
    }
}
