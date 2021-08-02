package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.domain.Author;

import java.util.List;

public interface AuthorService {
    List<Author> findAll();
}
