package ru.otus.spring.seryakova.repository;

import ru.otus.spring.seryakova.domain.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {
    Author save(Author author);

    Optional<Author> findById(long id);

    List<Author> findAll();
}
