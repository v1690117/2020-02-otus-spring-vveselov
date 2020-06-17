package com.v1690117.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Document(collection = "genres")
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Transient
    public static final String SEQUENCE_NAME = "genres_sequence";

    @Id
    @Field(name = "genre_id")
    private Long id;

    @Field(name = "name")
    private String name;

    public Genre(long id) {
        this(id, null);
    }

    public Genre(String name) {
        this(null, name);
    }

    @Override
    public String toString() {
        return String.format(
                "%s (%d)",
                name,
                id
        );
    }
}
