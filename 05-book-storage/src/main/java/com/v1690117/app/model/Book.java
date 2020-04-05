package com.v1690117.app.model;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Book {
    private final long id;
    private final String title;

    public Map<String, Object> map() {
        return new HashMap<String, Object>() {{
            put("id", id);
            put("title", title);
        }};
    }
}
