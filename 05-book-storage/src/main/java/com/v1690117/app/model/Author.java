package com.v1690117.app.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Author {
    private final long id;
    private final String firstName;
    private final String lastName;

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
