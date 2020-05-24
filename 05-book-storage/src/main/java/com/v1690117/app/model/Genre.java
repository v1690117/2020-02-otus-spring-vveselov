package com.v1690117.app.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Genre {
    private final long id;
    private final String name;

    public Map<String, Object> map() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("name", name);
        }};
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
