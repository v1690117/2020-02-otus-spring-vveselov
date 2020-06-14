package com.v1690117.app.dao;

import com.v1690117.app.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookRepository extends MongoRepository<Book, Long> {
    List<Book> findByAuthorsId(Long id);

    List<Book> findByGenresId(Long id);
}
