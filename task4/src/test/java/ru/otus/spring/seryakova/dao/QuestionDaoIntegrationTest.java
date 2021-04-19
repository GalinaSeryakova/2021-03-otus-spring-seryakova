package ru.otus.spring.seryakova.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.spring.seryakova.config.ApplicationSettings;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.service.CsvLoaderImpl;
import ru.otus.spring.seryakova.service.LocalizationService;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class QuestionDaoIntegrationTest {

    @Autowired
    private LocalizationService localizationService;

    @DisplayName("возвращает все вопросы")
    @Test
    public void test() {
        QuestionDao dao = new QuestionDaoCSV(localizationService, new CsvLoaderImpl());
        Collection<Question> questions = dao.getQuestions();
        assertNotNull(questions);
        assertEquals(5, questions.size());
    }
}
