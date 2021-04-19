package ru.otus.spring.seryakova.dao;

import org.springframework.stereotype.Component;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.exception.QuestionsLoadingException;
import ru.otus.spring.seryakova.service.CsvLoader;
import ru.otus.spring.seryakova.service.LocalizationService;

import java.io.IOException;
import java.util.Collection;

@Component
public class QuestionDaoCSV implements QuestionDao {

    private final String fileName;
    private final CsvLoader loader;

    public QuestionDaoCSV(LocalizationService localization, CsvLoader loader) {
        this.fileName = localization.getQuestionFile();
        this.loader = loader;
    }

    @Override
    public Collection<Question> getQuestions() {
        try {
            return loader.read(Question.class, fileName);
        } catch (IOException ex) {
            throw new QuestionsLoadingException(ex.getMessage(), ex);
        }
    }
}
