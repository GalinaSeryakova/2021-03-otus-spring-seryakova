package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.GenreDto;

import java.util.List;

public interface GenreService {
    List<GenreDto> findAll();
}
