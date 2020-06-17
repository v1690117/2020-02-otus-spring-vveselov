package com.v1690117.app.event;

import com.v1690117.app.dao.BookRepository;
import com.v1690117.app.model.Book;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeDeleteEvent;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@AllArgsConstructor
public class CascadeDeleteMongoEventListener extends AbstractMongoEventListener<Object> {
    private BookRepository bookRepository;

    @Override
    public void onBeforeDelete(BeforeDeleteEvent<Object> event) {
        if (Objects.equals(event.getCollectionName(), "authors")) {
            Long id = event.getSource().getLong("_id");
            List<Book> books = bookRepository.findByAuthorsId(id);
            if (books.size() > 0) {
                throw new RuntimeException(
                        String.format(
                                "Can't delete genre %d because it has references.",
                                id
                        )
                );
            }
        } else if (Objects.equals(event.getCollectionName(), "genres")) {
            Long id = event.getSource().getLong("_id");
            List<Book> books = bookRepository.findByGenresId(id);
            if (books.size() > 0) {
                throw new RuntimeException(
                        String.format(
                                "Can't delete genre %d because it has references.",
                                id
                        )
                );
            }
        }
    }
}
