package com.v1690117.app.bee.changelog;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.v1690117.app.model.Author;
import com.v1690117.app.model.Book;
import com.v1690117.app.model.Genre;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Collections;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addGenres", author = "v1690117")
    public void insertGenres(MongoTemplate mt) {
        mt.save(new Genre(1L, "drama"));
        mt.save(new Genre(2L, "comedy"));
        mt.save(new Genre(3L, "science"));
        mt.save(new Genre(3L, "scifi"));
        mt.save(new Genre(8L, "software development"));
    }

    @ChangeSet(order = "002", id = "addAuthors", author = "v1690117")
    public void insertAuthors(MongoTemplate mt) {
        mt.save(
                new Author(
                        2L,
                        "Egor",
                        "Bugaenko"
                )
        );
    }

    @ChangeSet(order = "003", id = "addBooks", author = "v1690117")
    public void insertBooks(MongoTemplate mt) {
        mt.save(
                new Book(
                        1L,
                        "Elegant Objects",
                        "... Elegant Objects ...",
                        "2016",
                        Collections.singletonList(
                                new Author(
                                        2L,
                                        "Egor",
                                        "Bugaenko"
                                )
                        ),
                        Collections.singletonList(
                                new Genre(8L, "software development")
                        ),
                        Collections.emptyList()
                )
        );
    }
}
