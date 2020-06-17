package com.v1690117.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "authors")
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Transient
    public static final String SEQUENCE_NAME = "authors_sequence";

    @Id
    @Field(name = "author_id")
    private Long id;

    @Field(name = "first_name")
    private String firstName;

    @Field(name = "last_name")
    private String lastName;

    public Author(long id) {
        this(id, null, null);
    }

    public Author(String firstName, String lastName) {
        this(
                null,
                firstName,
                lastName
        );
    }

    @Override
    public String toString() {
        return String.format(
                "%c. %s (%d)",
                firstName.charAt(0),
                lastName,
                id
        );
    }
}
