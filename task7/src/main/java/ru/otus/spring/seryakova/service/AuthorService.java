package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.dto.AuthorDto;

import java.util.List;

public interface AuthorService {
    List<AuthorDto> findAll();
}
