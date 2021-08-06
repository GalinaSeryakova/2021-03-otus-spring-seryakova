package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.GenreDto;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.GenreRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс GenreServiceImplTest")
public class GenreServiceImplTest {

    @Mock
    private GenreRepository genreRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private GenreServiceImpl genreService;

    @DisplayName("возвращает все жанры")
    @Test
    void shouldReturnAllGenres() {
        given(genreRepository.findAll())
                .willReturn(Arrays.asList(
                        new Genre(1L, "genre1"),
                        new Genre(2L, "genre2")));
        given(modelMapper.genreEntityListToDtoList(anyList()))
                .willReturn(Arrays.asList(
                        new GenreDto(1L, "genre1"),
                        new GenreDto(2L, "genre2")));

        List<GenreDto> genres = genreService.findAll();

        assertNotNull(genres);
        assertEquals(2, genres.size());
        verify(genreRepository, times(1)).findAll();

    }
}
