package ru.otus.spring.seryakova.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@DataJpaTest
@Import(GenreRepositoryJpa.class)
public class GenreRepositoryJpaTest {

    private static final long EXISTING_ID = 1;
    private static final String EXISTING_NAME = "Detective";

    @Autowired
    private GenreRepositoryJpa genreRepository;

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Genre expectedGenre = new Genre(EXISTING_ID, EXISTING_NAME);
        Genre actualGenre = genreRepository.findById(expectedGenre.getId()).orElse(null);
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Genre expectedGenre = new Genre(EXISTING_ID, EXISTING_NAME);
        List<Genre> actualGenreList = genreRepository.findAll();
        assertThat(actualGenreList.size()).isEqualTo(2);
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .contains(expectedGenre);
    }
}
