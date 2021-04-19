package ru.otus.spring.seryakova.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.service.CsvLoaderImpl;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class QuestionDaoIntegrationTest {

    @DisplayName("возвращает все вопросы")
    @Test
    public void test() {
        QuestionDao dao = new QuestionDaoCSV("questions.csv", new CsvLoaderImpl());
        Collection<Question> questions = dao.getQuestions();
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }
}
