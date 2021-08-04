package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.dto.BookDto;

import java.util.List;

public interface BookService {
    BookDto addNewBook(BookDto bookDto);

    List<BookDto> findAllBooks();

    BookDto findBookById(long id);

    void updateBook(BookDto bookDto);

    void deleteBookById(long id);
}
