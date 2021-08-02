package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.domain.Book;

import java.util.List;

public interface BookService {
    long addNewBook(Book book);

    List<Book> findAllBooks();

    Book findBookById(long id);

    void updateBook(Book targetInfo);

    void deleteBookById(long id);
}
