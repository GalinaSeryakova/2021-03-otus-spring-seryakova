package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Comment;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.dto.BookDto;
import ru.otus.spring.seryakova.dto.CommentDto;
import ru.otus.spring.seryakova.dto.GenreDto;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.CommentRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс CommentServiceImpl")
public class CommentServiceImplTest {

    private Book book1 = new Book(1L, "book1", new Author(1L, "author1"), new Genre(1L, "genre1"));
    private BookDto bookDto1 = new BookDto(1L, "book1", new AuthorDto(1L, "author1"), new GenreDto(1L, "genre1"));

    private Comment comm1 = new Comment(1L, "text1", book1);
    private Comment comm2 = new Comment(2L, "text2", book1);
    private CommentDto commDto1 = new CommentDto(1L, "text1", bookDto1);
    private CommentDto commDto2 = new CommentDto(2L, "text2", bookDto1);

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private CommentServiceImpl commentService;

    @DisplayName("возвращает все комментарии")
    @Test
    void shouldReturnAllGenres() {
        given(commentRepository.findAll()).willReturn(Arrays.asList(comm1,comm2));
        given(modelMapper.commentEntitiesToDtoList(anyList())).willReturn(Arrays.asList(commDto1,commDto2));

        List<CommentDto> comments = commentService.findAllComments();

        assertNotNull(comments);
        assertEquals(2, comments.size());
        verify(commentRepository, times(1)).findAll();

    }

    @DisplayName("возвращает комментарий по его id")
    @Test
    void shouldReturnExpectedCommentById() {
        given(commentRepository.findById(anyLong())).willReturn(Optional.of(comm1));
        given(modelMapper.entityToDto(same(comm1))).willReturn(commDto1);

        CommentDto expectedComment = commDto1;
        CommentDto actualComment = commentService.findCommentById(expectedComment.getId());
        verify(commentRepository, times(1)).findById(anyLong());
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("удаляет комментарий по его id")
    @Test
    void shouldCorrectDeleteCommentById() {
        commentService.deleteCommentById(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }

    @DisplayName("возвращает все комментарии книги")
    @Test
    void shouldReturnCommentsByBookId() {
        given(commentRepository.findCommentsByBookId(anyLong())).willReturn(Arrays.asList(comm1,comm2));
        given(modelMapper.commentEntitiesToDtoList(anyList())).willReturn(Arrays.asList(commDto1,commDto2));

        List<CommentDto> comments = commentService.findCommentsByBookId(1L);

        assertNotNull(comments);
        assertEquals(2, comments.size());
        verify(commentRepository, times(1)).findCommentsByBookId(anyLong());

    }

    @DisplayName("удаляет комментарии книги")
    @Test
    void shouldCorrectDeleteCommentByBookId() {
        commentService.deleteCommentByBookId(1L);
        verify(commentRepository, times(1)).deleteCommentsByBookId(1L);
    }
}
