package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.dto.BookDto;
import ru.otus.spring.seryakova.dto.GenreDto;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.BookRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс BookServiceImpl")
public class BookServiceImplTest {

    private Book book1 = new Book(1L, "book1", new Author(1L, "author1"), new Genre(1L, "genre1"));
    private Book book2 = new Book(2L, "book2", new Author(1L, "author2"), new Genre(2L, "genre2"));
    private BookDto bookDto1 = new BookDto(1L, "book1", new AuthorDto(1L, "author1"), new GenreDto(1L, "genre1"));
    private BookDto bookDto2 = new BookDto(2L, "book2", new AuthorDto(1L, "author2"), new GenreDto(2L, "genre2"));

    @Mock
    private BookRepository bookRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private BookServiceImpl bookService;

    @DisplayName("возвращает все книги")
    @Test
    void shouldReturnAllBooks() {
        given(bookRepository.findAll()).willReturn(Arrays.asList(book1,book2));
        given(modelMapper.bookEntityListToDtoList(anyList())).willReturn(Arrays.asList(bookDto1,bookDto2));

        List<BookDto> books = bookService.findAllBooks();

        assertNotNull(books);
        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();

    }

    @DisplayName("возвращает книгу по её id")
    @Test
    void shouldReturnExpectedBookById() {
        given(bookRepository.findById(anyLong())).willReturn(Optional.of(book1));
        given(modelMapper.entityToDto(same(book1))).willReturn(bookDto1);

        BookDto expectedBook = bookDto1;
        BookDto actualBook = bookService.findBookById(expectedBook.getId());
        verify(bookRepository, times(1)).findById(anyLong());
        assertThat(actualBook).usingRecursiveComparison().isEqualTo(expectedBook);
    }

    @DisplayName("удаляет книгу по её id")
    @Test
    void shouldCorrectDeleteBookById() {
        bookService.deleteBookById(1L);
        verify(bookRepository, times(1)).deleteById(1L);
    }
}
