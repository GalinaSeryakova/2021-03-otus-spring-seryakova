package ru.otus.spring.seryakova.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.service.CsvLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@Component
public class QuestionDaoCSV implements QuestionDao {

    private final String fileName;
    private final CsvLoader loader;

    public QuestionDaoCSV(@Value("${questions.path}")String fileName, CsvLoader loader) {
        this.fileName = fileName;
        this.loader = loader;
    }

    public Collection<Question> getQuestions() throws IOException {
        return loadQuestions();
    }

    private Collection<Question> loadQuestions() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);
        return loader.read(Question.class, inputStream);
    }
}
