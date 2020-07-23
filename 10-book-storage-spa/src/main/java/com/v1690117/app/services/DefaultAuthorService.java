package com.v1690117.app.services;

import com.v1690117.app.dao.AuthorRepository;
import com.v1690117.app.model.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAuthorService implements AuthorService {
    private final AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(long id) {
        return authorRepository.findById(id).get();
    }

    @Override
    public Author insert(Author author) {
        if (author.getLastName() == null || author.getLastName().trim().isEmpty())
            throw new IllegalArgumentException("Last name can not be empty!");
        return authorRepository.save(author);
    }

    @Override
    public Author update(Author given) {
        Author existed = authorRepository.findById(given.getId()).get();
        String firstName = given.getFirstName() == null ?
                existed.getFirstName()
                : given.getFirstName();
        String lastName = given.getLastName() == null ?
                existed.getLastName()
                : given.getLastName();
        return authorRepository.save(
                new Author(
                        given.getId(),
                        firstName,
                        lastName
                )
        );
    }

    @Override
    public void delete(long id) {
        authorRepository.delete(
                authorRepository.findById(id).get()
        );
    }
}
