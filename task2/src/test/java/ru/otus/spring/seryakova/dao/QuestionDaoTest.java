package ru.otus.spring.seryakova.dao;

import org.junit.Test;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.service.CsvLoaderImpl;

import java.io.IOException;
import java.util.Collection;

import static org.junit.Assert.*;

public class QuestionDaoTest {

    @Test
    public void test() throws IOException {
        QuestionDao dao = new QuestionDaoCSV("questions.csv", new CsvLoaderImpl());
        Collection<Question> questions = dao.getQuestions();
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }
}
