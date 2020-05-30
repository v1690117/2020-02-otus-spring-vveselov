package com.v1690117.app.dao.jdbc.mappers;

import com.v1690117.app.model.Book;
import org.springframework.jdbc.core.RowMapper;

public interface BookMapperProvider {
    RowMapper<Book> mapper();
}
