package ru.otus.spring.seryakova.repository;

import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Genre save(Genre genre);
    Optional<Genre> findById(long id);
    List<Genre> findAll();
}
