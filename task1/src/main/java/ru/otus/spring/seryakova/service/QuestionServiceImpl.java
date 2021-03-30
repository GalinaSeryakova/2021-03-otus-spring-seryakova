package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.dao.QuestionDao;
import ru.otus.spring.seryakova.domain.Question;

import java.io.IOException;
import java.util.Collection;

public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    public void printQuestions() throws IOException {
        Collection<Question> questions = dao.getQuestions();
        for (Question question : questions) {
            System.out.println(question);
        }
    }
}
