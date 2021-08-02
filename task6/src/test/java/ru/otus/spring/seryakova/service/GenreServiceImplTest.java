package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.seryakova.dao.GenreDao;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс GenreServiceImplTest")
public class GenreServiceImplTest {

    @Mock
    private GenreDao genreDao;
    @InjectMocks
    private GenreServiceImpl genreService;

    @DisplayName("возвращает все жанры")
    @Test
    void shouldReturnAllGenres() {
        given(genreDao.getAll())
                .willReturn(Arrays.asList(
                        new Genre(1L, "genre1"),
                        new Genre(2L, "genre2")));

        List<Genre> genres = genreService.findAll();

        assertNotNull(genres);
        assertEquals(2, genres.size());
        verify(genreDao, times(1)).getAll();

    }
}
