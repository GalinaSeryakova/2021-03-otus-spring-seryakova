package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.seryakova.domain.Question;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("Класс CsvReaderImpl")
public class CsvReaderImplTest {

    @DisplayName("возвращает все вопросы")
    @Test
    public void shouldReadAllQuestions() throws IOException {
        CsvReaderImpl dao = new CsvReaderImpl();
        Collection<Question> questions = dao.read(Question.class,
                new ByteArrayInputStream("questionText,answerText\n2+2,4\n2-2,0".getBytes()));
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }
}
