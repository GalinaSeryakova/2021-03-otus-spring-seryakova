package ru.otus.spring.seryakova.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с книгами должно")
@DataJpaTest
@Import(BookRepositoryJpa.class)
public class BookRepositoryJpaTest {

    private static final long EXISTING_ID = 1;
    private static final String EXISTING_NAME = "The Three Musketeers";
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Alexandre Dumas";
    private static final long EXISTING_GENRE_ID = 2;
    private static final String EXISTING_GENRE_NAME = "History";

    private static final long INCORRECT_GIVEN_ID = 1111;

    @Autowired
    private BookRepositoryJpa bookRepository;

    @DisplayName("возвращать ожидаемый список книг")
    @Test
    void shouldReturnExpectedBooksList() {
        Book expectedBook = new Book(EXISTING_ID, EXISTING_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        List<Book> actualBookList = bookRepository.findAll();
        assertThat(actualBookList.size()).isEqualTo(2);
        assertThat(actualBookList)
                .usingFieldByFieldElementComparator().contains(expectedBook);
    }

    @DisplayName("возвращать ожидаемую книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = new Book(EXISTING_ID, EXISTING_NAME,
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        Book actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("возвращать null по не корректному id")
    @Test
    void shouldReturnNullWithIncorrectId() {
        assertThat(bookRepository.findById(INCORRECT_GIVEN_ID).orElse(null)).isNull();
    }

    @DisplayName("добавлять книгу в БД")
    @Test
    void shouldInsertBook() {
        Book book = new Book(0, "New book",
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        long newId = bookRepository.save(book).getId();

        Book expectedBook = new Book(newId, "New book",
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));

        Book actualBook = bookRepository.findById(newId).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("изменять книгу")
    @Test
    void shouldModifyBook() {
        Book expectedBook = new Book(EXISTING_ID, "Updated book",
                new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME));
        bookRepository.save(expectedBook);
        Book actualBook = bookRepository.findById(expectedBook.getId()).orElse(null);
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удалять заданную книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        bookRepository.deleteById(EXISTING_ID);
        assertThat(bookRepository.findById(EXISTING_ID).orElse(null)).isNull();
    }
}
