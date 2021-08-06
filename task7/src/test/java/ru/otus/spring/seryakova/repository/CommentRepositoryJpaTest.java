package ru.otus.spring.seryakova.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Comment;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Repository для работы с комментариями должно")
@DataJpaTest
@Import(CommentRepositoryJpa.class)
public class CommentRepositoryJpaTest {

    private static final long EXISTING_ID = 1;
    private static final String EXISTING_NAME = "my test comment";
    private static final long EXISTING_BOOK_ID = 1;
    private static final String EXISTING_BOOK_NAME = "The Three Musketeers";
    private static final long EXISTING_AUTHOR_ID = 1;
    private static final String EXISTING_AUTHOR_NAME = "Alexandre Dumas";
    private static final long EXISTING_GENRE_ID = 2;
    private static final String EXISTING_GENRE_NAME = "History";

    private static final long INCORRECT_GIVEN_ID = 1111;

    @Autowired
    private CommentRepositoryJpa commentRepository;

    @DisplayName("возвращать ожидаемый список комментариев")
    @Test
    void shouldReturnExpectedCommentsList() {
        Comment expectedComment = new Comment(EXISTING_ID, EXISTING_NAME,
                new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                        new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                        new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));
        List<Comment> actualCommentList = commentRepository.findAll();
        assertThat(actualCommentList.size()).isEqualTo(1);
        assertThat(actualCommentList)
                .usingFieldByFieldElementComparator().containsExactly(expectedComment);
    }

    @DisplayName("возвращать ожидаемый комментарий по его id")
    @Test
    void shouldReturnExpectedCommentById() {
        Comment expectedComment = new Comment(EXISTING_ID, EXISTING_NAME,
                new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                        new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                        new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));
        Comment actualComment = commentRepository.findById(expectedComment.getId()).orElse(null);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("возвращать null по не корректному id")
    @Test
    void shouldReturnNullWithIncorrectId() {
        assertThat(commentRepository.findById(INCORRECT_GIVEN_ID).orElse(null)).isNull();
    }

    @DisplayName("добавлять комментарий в БД")
    @Test
    void shouldInsertComment() {
        Comment Comment = new Comment(0, "New Comment",
                new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                        new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                        new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));
        long newId = commentRepository.save(Comment).getId();

        Comment expectedComment = new Comment(newId, "New Comment",
                new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                        new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                        new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));

        Comment actualComment = commentRepository.findById(newId).orElse(null);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("изменять изменять")
    @Test
    void shouldModifyComment() {
        Comment expectedComment = new Comment(EXISTING_ID, "Updated text",
                new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                        new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                        new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));
        commentRepository.save(expectedComment);
        Comment actualComment = commentRepository.findById(expectedComment.getId()).orElse(null);
        assertThat(actualComment).usingRecursiveComparison().isEqualTo(expectedComment);
    }

    @DisplayName("удалять заданный комментарий по его id")
    @Test
    void shouldCorrectDeleteCommentById() {
        commentRepository.deleteById(EXISTING_ID);
        assertThat(commentRepository.findById(EXISTING_ID).orElse(null)).isNull();
    }

    @DisplayName("возвращать ожидаемый список комментариев по id книги")
    @Test
    void shouldReturnExpectedCommentsListByBookId() {
        Comment expectedComment = new Comment(EXISTING_ID, EXISTING_NAME,
                new Book(EXISTING_BOOK_ID, EXISTING_BOOK_NAME,
                        new Author(EXISTING_AUTHOR_ID, EXISTING_AUTHOR_NAME),
                        new Genre(EXISTING_GENRE_ID, EXISTING_GENRE_NAME)));
        List<Comment> actualCommentList = commentRepository.findCommentsByBookId(EXISTING_BOOK_ID);
        assertThat(actualCommentList.size()).isEqualTo(1);
        assertThat(actualCommentList)
                .usingFieldByFieldElementComparator().containsExactly(expectedComment);

        actualCommentList = commentRepository.findCommentsByBookId(11L);
        assertThat(actualCommentList).isEmpty();
    }

    @DisplayName("удалять комментарии по id книги")
    @Test
    void shouldCorrectDeleteCommentsByBookId() {
        commentRepository.deleteCommentsByBookId(EXISTING_BOOK_ID);
        assertThat(commentRepository.findCommentsByBookId(EXISTING_BOOK_ID)).isEmpty();
    }
}
