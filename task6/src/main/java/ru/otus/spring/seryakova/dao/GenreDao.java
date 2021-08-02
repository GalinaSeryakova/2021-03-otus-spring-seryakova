package ru.otus.spring.seryakova.dao;

import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;

public interface GenreDao {

    Genre getById(long id);

    List<Genre> getAll();
}
