package ru.otus.spring.seryakova.mapper;

import org.mapstruct.*;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Comment;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.dto.BookDto;
import ru.otus.spring.seryakova.dto.CommentDto;
import ru.otus.spring.seryakova.dto.GenreDto;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.ERROR
)
public interface ModelMapper {

    BookDto entityToDto(Book book);

    AuthorDto entityToDto(Author author);

    GenreDto entityToDto(Genre genre);

    CommentDto entityToDto(Comment comment);

    List<BookDto> bookEntityListToDtoList(List<Book> books);

    List<GenreDto> genreEntityListToDtoList(List<Genre> genres);

    List<AuthorDto> authorEntityListToDtoList(List<Author> authors);

    List<CommentDto> commentEntitiesToDtoList(List<Comment> comments);

    Author dtoToEntity(AuthorDto authorDto);

    Genre dtoToEntity(GenreDto genreDto);

    Book dtoToEntity(BookDto bookDto);

    Comment dtoToEntity(CommentDto commentDto);
}
