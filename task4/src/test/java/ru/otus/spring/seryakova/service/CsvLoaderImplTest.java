package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.seryakova.domain.Question;

import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс CsvLoaderImpl")
public class CsvLoaderImplTest {

    @DisplayName("возвращает все вопросы")
    @Test
    public void shouldReadAllQuestions() throws IOException {
        CsvLoaderImpl dao = new CsvLoaderImpl();
        Collection<Question> questions = dao.read(Question.class, "questions.csv");
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }
}
