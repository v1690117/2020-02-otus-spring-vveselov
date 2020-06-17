package com.v1690117.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "book_comments")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Transient
    public static final String SEQUENCE_NAME = "comments_sequence";

    @Id
    @Field(name = "comment_id")
    private Long id;

    @Field(name = "text")
    private String text;

    private Book book;

    public Comment(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return String.format(
                "%d) %s",
                id,
                text
        );
    }
}
