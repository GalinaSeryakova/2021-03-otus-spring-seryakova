package ru.otus.spring.seryakova.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.exception.QuestionsLoadingException;
import ru.otus.spring.seryakova.service.CsvReader;
import ru.otus.spring.seryakova.service.FileLoader;

import java.io.IOException;
import java.util.Collection;

@Component
public class QuestionDaoCSV implements QuestionDao {

    private final String fileName;
    private final FileLoader loader;
    private final CsvReader reader;

    public QuestionDaoCSV(@Value("${questions.path}")String fileName, FileLoader loader, CsvReader reader) {
        this.fileName = fileName;
        this.loader = loader;
        this.reader = reader;
    }

    @Override
    public Collection<Question> getQuestions() {
        try {
            return reader.read(Question.class, loader.getAsStream(fileName));
        } catch (IOException ex) {
            throw new QuestionsLoadingException(ex.getMessage(), ex);
        }
    }
}
