package com.v1690117.app.services;

import com.v1690117.app.dao.AuthorDao;
import com.v1690117.app.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAuthorService implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public List<Author> findAll() {
        return authorDao.findAll();
    }

    @Override
    public Author findById(long id) {
        return authorDao.findById(id);
    }

    @Override
    public void insert(Author author) {
        if (author.getLastName() == null || author.getLastName().trim().isEmpty())
            throw new IllegalArgumentException("Last name can not be empty!");
        authorDao.insert(
                new Author(
                        authorDao.count() + 1,
                        author.getFirstName(),
                        author.getLastName()
                )
        );
    }

    @Override
    public void update(Author given) {
        Author existed = authorDao.findById(given.getId());
        String firstName = given.getFirstName() == null ?
                existed.getFirstName()
                : given.getFirstName();
        String lastName = given.getLastName() == null ?
                existed.getLastName()
                : given.getLastName();
        authorDao.update(
                new Author(
                        given.getId(),
                        firstName,
                        lastName
                )
        );
    }

    @Override
    public void delete(long id) {
        authorDao.delete(id);
    }
}
