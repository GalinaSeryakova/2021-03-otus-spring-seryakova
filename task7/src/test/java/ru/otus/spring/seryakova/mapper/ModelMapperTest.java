package ru.otus.spring.seryakova.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Comment;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.dto.BookDto;
import ru.otus.spring.seryakova.dto.CommentDto;
import ru.otus.spring.seryakova.dto.GenreDto;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

@DisplayName("ModelMapper ")
@ExtendWith(SpringExtension.class)
class ModelMapperTest {

    private Author author = new Author(1L, "author1");
    private AuthorDto authorDto = new AuthorDto(1L, "author1");
    private Genre genre = new Genre(1L, "genre1");
    private GenreDto genreDto = new GenreDto(1L, "genre1");
    private Book book = new Book(1L, "book1", new Author(1L, "author1"), new Genre(1L, "genre1"));
    private BookDto bookDto = new BookDto(1L, "book1", new AuthorDto(1L, "author1"), new GenreDto(1L, "genre1"));
    private Comment comm = new Comment(1L, "text1", book);
    private CommentDto commDto = new CommentDto(1L, "text1", bookDto);

    @Configuration
    @ComponentScan(basePackageClasses = {ModelMapper.class, ModelMapperImpl.class, ModelMapperTest.class})
    public static class ModelMapperSpringTestConfiguration {
    }

    @Autowired
    private ModelMapper modelMapper;

    @DisplayName("не должен быть null")
    @Test
    void bookMapperIsNotNull() {
        assertThat(modelMapper).isNotNull();
    }

    @DisplayName("должен иметь в dto те же параметры автора")
    @Test
    void shouldHaveSameAuthorInSourceEntityAndTargetDTO() {
        AuthorDto actualDto = modelMapper.entityToDto(author);
        if (actualDto == null) {
            fail("authorDto is null");
        }
        assertThat(actualDto).isEqualTo(authorDto);
    }

    @DisplayName("должен иметь в dto те же параметры жанра")
    @Test
    void shouldHaveSameGenreInSourceEntityAndTargetDTO() {
        GenreDto actualDto = modelMapper.entityToDto(genre);
        if (actualDto == null) {
            fail("genreDto is null");
        }
        assertThat(actualDto).isEqualTo(genreDto);
    }

    @DisplayName("должен иметь в dto те же параметры книги")
    @Test
    void shouldHaveSameBookInSourceEntityAndTargetDTO() {
        BookDto actualDto = modelMapper.entityToDto(book);
        if (actualDto == null) {
            fail("bookDto is null");
        }
        assertThat(actualDto).isEqualTo(bookDto);
    }

    @DisplayName("должен иметь в dto те же параметры комментария")
    @Test
    void shouldHaveSameCommentInSourceEntityAndTargetDTO() {
        CommentDto actualDto = modelMapper.entityToDto(comm);
        if (actualDto == null) {
            fail("commentDto is null");
        }
        assertThat(actualDto).isEqualTo(commDto);
    }

    @DisplayName("должен иметь те же параметры автора, что и в dto")
    @Test
    void shouldHaveSameAuthorInSourceDTOAndTargetEntity() {
        Author actual = modelMapper.dtoToEntity(authorDto);
        if (actual == null) {
            fail("author is null");
        }
        assertThat(actual).isEqualTo(author);
    }

    @DisplayName("должен иметь те же параметры жанра, что и в dto")
    @Test
    void shouldHaveSameGenreInSourceDTOAndTargetEntity() {
        Genre actual = modelMapper.dtoToEntity(genreDto);
        if (actual == null) {
            fail("genre is null");
        }
        assertThat(actual).isEqualTo(genre);
    }

    @DisplayName("должен иметь те же параметры книги, что и в dto")
    @Test
    void shouldHaveSameBookInSourceDTOAndTargetEntity() {
        Book actual = modelMapper.dtoToEntity(bookDto);
        if (actual == null) {
            fail("book is null");
        }
        assertThat(actual).isEqualTo(book);
    }

    @DisplayName("должен иметь те же параметры комментария, что и в dto")
    @Test
    void shouldHaveSameCommentInSourceDTOAndTargetEntity() {
        Comment actual = modelMapper.dtoToEntity(commDto);
        if (actual == null) {
            fail("comment is null");
        }
        assertThat(actual).isEqualTo(comm);
    }

    @DisplayName("должен иметь тех же авторов в списке dto, что и в списке entity")
    @Test
    void shouldHaveSameAuthorListInSourceEntityAndTargetDTO() {
        List<AuthorDto> actualDtos = modelMapper.authorEntityListToDtoList(Arrays.asList(author));
        if (actualDtos == null) {
            fail("authorDto list is null or empty");
        }
        assertThat(actualDtos).usingFieldByFieldElementComparator().containsExactly(authorDto);
    }

    @DisplayName("должен иметь те же жанры в списке dto, что и в списке entity")
    @Test
    void shouldHaveSameGenreListInSourceEntityAndTargetDTO() {
        List<GenreDto> actualDtos = modelMapper.genreEntityListToDtoList(Arrays.asList(genre));
        if (actualDtos == null) {
            fail("genreDto list is null or empty");
        }
        assertThat(actualDtos).usingFieldByFieldElementComparator().containsExactly(genreDto);
    }

    @DisplayName("должен иметь те же книги в списке dto, что и в списке entity")
    @Test
    void shouldHaveSameBookListInSourceEntityAndTargetDTO() {
        List<BookDto> actualDtos = modelMapper.bookEntityListToDtoList(Arrays.asList(book));
        if (actualDtos == null) {
            fail("bookDto list is null or empty");
        }
        assertThat(actualDtos).usingFieldByFieldElementComparator().containsExactly(bookDto);
    }

    @DisplayName("должен иметь те же комментарии в списке dto, что и в списке entity")
    @Test
    void shouldHaveSameCommentListInSourceEntityAndTargetDTO() {
        List<CommentDto> actualDtos = modelMapper.commentEntitiesToDtoList(Arrays.asList(comm));
        if (actualDtos == null) {
            fail("commentDto list is null or empty");
        }
        assertThat(actualDtos).usingFieldByFieldElementComparator().containsExactly(commDto);
    }
}