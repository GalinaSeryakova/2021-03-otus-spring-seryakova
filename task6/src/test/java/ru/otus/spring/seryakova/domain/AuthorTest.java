package ru.otus.spring.seryakova.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("class Author")
class AuthorTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void correctClassCreate() {
        Author author = new Author(Long.valueOf(10), "Ivan");

        assertThat(author).isNotNull().isInstanceOf(Author.class);
        assertAll(
                () -> assertEquals(Long.valueOf(10), author.getId()),
                () -> assertEquals("Ivan", author.getName())
        );
    }
}
