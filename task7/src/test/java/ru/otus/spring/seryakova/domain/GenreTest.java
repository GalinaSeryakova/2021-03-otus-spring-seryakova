package ru.otus.spring.seryakova.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("class Genre")
public class GenreTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void correctClassCreate() {
        Genre genre = new Genre(Long.valueOf(10), "Drama");

        assertThat(genre).isNotNull().isInstanceOf(Genre.class);
        assertAll(
                () -> assertEquals(Long.valueOf(10), genre.getId()),
                () -> assertEquals("Drama", genre.getName())
        );
    }
}
