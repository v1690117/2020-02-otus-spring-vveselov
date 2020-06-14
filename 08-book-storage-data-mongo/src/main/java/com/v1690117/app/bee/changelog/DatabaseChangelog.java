package com.v1690117.app.bee.changelog;

import com.github.mongobee.changeset.ChangeLog;
import com.github.mongobee.changeset.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import java.util.Collections;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "addGenres", author = "v1690117")
    public void insertGenres(DB db) {
        DBCollection myCollection = db.getCollection("genres");
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 1L)
                        .append("name", "drama")
        );
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 2L)
                        .append("name", "comedy")
        );
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 3L)
                        .append("name", "science")
        );
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 4L)
                        .append("name", "scifi")
        );
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 5L)
                        .append("name", "software development")
        );
    }

    @ChangeSet(order = "002", id = "addAuthors", author = "v1690117")
    public void insertAuthors(DB db) {
        DBCollection myCollection = db.getCollection("authors");
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 2L)
                        .append("first_name", "Egor")
                        .append("last_name", "Bugaenko")
        );
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 3L)
                        .append("first_name", "Robert")
                        .append("last_name", "Nystrom")
        );
    }

    @ChangeSet(order = "003", id = "addBooks", author = "v1690117")
    public void insertBooks(DB db) {
        DBCollection myCollection = db.getCollection("books");
        myCollection.insert(
                new BasicDBObject()
                        .append("_id", 1L)
                        .append("title", "Elegant Objects")
                        .append("annotation", "... Elegant Objects ...")
                        .append("year", "2016")
                        .append("genres", Collections.singletonList(
                                new BasicDBObject()
                                        .append("_id", 8L)
                                        .append("name", "software development")
                        ))
                        .append("authors", Collections.singletonList(
                                new BasicDBObject()
                                        .append("_id", 2L)
                                        .append("first_name", "Egor")
                                        .append("last_name", "Bugaenko")
                        ))
                        .append("comments", Collections.emptyList())
        );
    }
}
