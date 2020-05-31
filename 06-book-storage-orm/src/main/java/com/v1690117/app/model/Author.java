package com.v1690117.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Data
@Entity
@Table(name = "authors")
@NoArgsConstructor
@AllArgsConstructor
public class Author {
    @Id
    @Column(name = "author_id")
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;

    public Map<String, Object> map() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("firstName", firstName);
            put("lastName", lastName);
        }};
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