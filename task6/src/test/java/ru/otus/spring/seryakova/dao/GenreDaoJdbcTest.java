package ru.otus.spring.seryakova.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с жанрами должно")
@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    private static final long EXISTING_ID = 1;
    private static final String EXISTING_NAME = "Detective";

    @Autowired
    private GenreDaoJdbc genreDao;

    @DisplayName("возвращать ожидаемый жанр по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Genre expectedGenre = new Genre(EXISTING_ID, EXISTING_NAME);
        Genre actualGenre = genreDao.getById(expectedGenre.getId());
        assertThat(actualGenre).usingRecursiveComparison().isEqualTo(expectedGenre);
    }

    @DisplayName("возвращать ожидаемый список жанров")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Genre expectedGenre = new Genre(EXISTING_ID, EXISTING_NAME);
        List<Genre> actualGenreList = genreDao.getAll();
        assertThat(actualGenreList.size()).isEqualTo(2);
        assertThat(actualGenreList)
                .usingFieldByFieldElementComparator()
                .contains(expectedGenre);
    }
}
