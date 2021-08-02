package ru.otus.spring.seryakova.dao;

import ru.otus.spring.seryakova.domain.Author;

import java.util.List;

public interface AuthorDao {

    Author getById(long id);

    List<Author> getAll();
}
