package com.v1690117.app.dao.jdbc.mappers;

import com.v1690117.app.model.Author;
import org.springframework.jdbc.core.RowMapper;

public interface AuthorMapperProvider {
    RowMapper<Author> mapper();
}
