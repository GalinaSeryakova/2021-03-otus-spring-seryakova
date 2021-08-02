package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.seryakova.dao.AuthorDao;
import ru.otus.spring.seryakova.dao.BookDao;
import ru.otus.spring.seryakova.dao.GenreDao;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс GenreServiceImplTest")
public class BookServiceImplTest {

    private Book book1 = new Book(1L, "book1", new Author(1L, "author1"), new Genre(1L, "genre1"));
    private Book book2 = new Book(2L, "book2", new Author(1L, "author2"), new Genre(2L, "genre2"));

    @Mock
    private BookDao bookDao;
    @Mock
    private AuthorDao authorDao;
    @Mock
    private GenreDao genreDao;
    @InjectMocks
    private BookServiceImpl bookService;

    @DisplayName("возвращает все книги")
    @Test
    void shouldReturnAllGenres() {
        given(bookDao.getAll()).willReturn(Arrays.asList(book1,book2));

        List<Book> books = bookService.findAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookDao, times(1)).getAll();

    }

    @DisplayName("возвращает книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        Book expectedBook = book1;
        given(bookDao.getById(anyLong())).willReturn(book1);
        Book actualBook = bookService.findBookById(expectedBook.getId());
        verify(bookDao, times(1)).getById(anyLong());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удаляет книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        bookService.deleteBookById(1L);
        verify(bookDao, times(1)).deleteById(1L);
    }
}
