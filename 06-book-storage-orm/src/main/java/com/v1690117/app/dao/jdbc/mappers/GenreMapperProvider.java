package com.v1690117.app.dao.jdbc.mappers;

import com.v1690117.app.model.Genre;
import org.springframework.jdbc.core.RowMapper;

public interface GenreMapperProvider {
    RowMapper<Genre> mapper();
}
