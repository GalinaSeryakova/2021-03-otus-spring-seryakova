package ru.otus.spring.seryakova.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.spring.seryakova.domain.Author;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Dao для работы с авторами должно")
@DataJpaTest
@Import(AuthorRepositoryJpa.class)
class AuthorRepositoryJpaTest {

    private static final long EXISTING_ID = 1;
    private static final String EXISTING_NAME = "Alexandre Dumas";

    @Autowired
    private AuthorRepositoryJpa authorRepository;

    @DisplayName("возвращать ожидаемого автора по его id")
    @Test
    void shouldReturnExpectedAuthorById() {
        Author expectedAuthor = new Author(EXISTING_ID, EXISTING_NAME);
        Author actualAuthor = authorRepository.findById(expectedAuthor.getId()).orElse(null);
        assertThat(actualAuthor).usingRecursiveComparison().isEqualTo(expectedAuthor);
    }

    @DisplayName("возвращать ожидаемый список авторов")
    @Test
    void shouldReturnExpectedAuthorsList() {
        Author expectedAuthor = new Author(EXISTING_ID, EXISTING_NAME);
        List<Author> actualAuthorList = authorRepository.findAll();
        assertThat(actualAuthorList.size()).isEqualTo(2);
        assertThat(actualAuthorList)
                .usingFieldByFieldElementComparator().contains(expectedAuthor);
    }

}