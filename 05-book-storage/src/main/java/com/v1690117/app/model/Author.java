package com.v1690117.app.model;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public class Author {
    private final long id;
    private final String firstName;
    private final String lastName;

    public Map<String, Object> map() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("first_name", firstName);
            put("last_name", lastName);
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
