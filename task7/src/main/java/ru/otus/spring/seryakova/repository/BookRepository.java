package ru.otus.spring.seryakova.repository;

import ru.otus.spring.seryakova.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book);

    Optional<Book> findById(long bookId);

    List<Book> findAll();

    void deleteById(long id);
}
