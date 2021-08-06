package ru.otus.spring.seryakova.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("class Comment")
public class CommentTest {

    @DisplayName("корректно создается конструктором")
    @Test
    void correctClassCreate() {
        Author author = new Author(10L, "Ivan");
        Genre genre = new Genre(11L, "Drama");
        Book book = new Book(12L, "Book name", author, genre);
        Comment comment = new Comment(20L, "text", book);

        assertThat(comment).isNotNull().isInstanceOf(Comment.class);
        assertThat(comment.getBook()).isNotNull().isInstanceOf(Book.class);
        assertThat(comment.getBook().getAuthor()).isNotNull().isInstanceOf(Author.class);
        assertThat(comment.getBook().getGenre()).isNotNull().isInstanceOf(Genre.class);
        assertAll(
                () -> assertEquals(20L, comment.getId()),
                () -> assertEquals("text", comment.getText())
        );
    }
}
