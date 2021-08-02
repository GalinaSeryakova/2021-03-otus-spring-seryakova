package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();
}
