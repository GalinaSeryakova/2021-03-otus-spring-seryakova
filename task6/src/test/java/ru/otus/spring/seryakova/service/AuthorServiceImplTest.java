package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.seryakova.dao.AuthorDao;
import ru.otus.spring.seryakova.domain.Author;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс AuthorServiceImplTest")
public class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;
    @InjectMocks
    private AuthorServiceImpl authorService;

    @DisplayName("возвращает всех авторов")
    @Test
    void shouldReturnAllAuthors() {
        given(authorDao.getAll())
                .willReturn(Arrays.asList(
                        new Author(1L, "author1"),
                        new Author(2L, "author2")));

        List<Author> authors = authorService.findAll();

        assertNotNull(authors);
        assertEquals(2, authors.size());
        verify(authorDao, times(1)).getAll();

    }

}
