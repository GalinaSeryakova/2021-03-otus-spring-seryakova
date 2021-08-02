package ru.otus.spring.seryakova.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("class Book")
public class BookTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void correctClassCreate() {
        Author author = new Author(Long.valueOf(10), "Ivan");
        Genre genre = new Genre(Long.valueOf(11), "Drama");
        Book book = new Book(Long.valueOf(12), "Book name", author, genre);

        assertThat(book).isNotNull().isInstanceOf(Book.class);
        assertThat(book.getAuthor()).isNotNull().isInstanceOf(Author.class);
        assertThat(book.getGenre()).isNotNull().isInstanceOf(Genre.class);
        assertAll(
                () -> assertEquals(Long.valueOf(12), book.getId()),
                () -> assertEquals("Book name", book.getName())
        );
    }
}
