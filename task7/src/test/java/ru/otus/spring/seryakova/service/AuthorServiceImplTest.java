package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.Import;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.mapper.ModelMapperImpl;
import ru.otus.spring.seryakova.repository.AuthorRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс AuthorServiceImplTest")
public class AuthorServiceImplTest {

    @Mock
    private AuthorRepository authorRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @DisplayName("возвращает всех авторов")
    @Test
    void shouldReturnAllAuthors() {
        given(authorRepository.findAll())
                .willReturn(Arrays.asList(
                        new Author(1L, "author1"),
                        new Author(2L, "author2")));
        given(modelMapper.authorEntityListToDtoList(anyList()))
                .willReturn(Arrays.asList(
                        new AuthorDto(1L, "author1"),
                        new AuthorDto(2L, "author2")));

        List<AuthorDto> authors = authorService.findAll();

        assertNotNull(authors);
        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();

    }

}
