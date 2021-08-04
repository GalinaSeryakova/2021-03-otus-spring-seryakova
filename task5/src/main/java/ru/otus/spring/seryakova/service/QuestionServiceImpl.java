package ru.otus.spring.seryakova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.dao.QuestionDao;
import ru.otus.spring.seryakova.domain.Question;

import java.util.Collection;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    @Autowired
    public QuestionServiceImpl(QuestionDao dao, IOProviderService ioService) {
        this.dao = dao;
    }

    public Collection<Question> getQuestions() {
        return dao.getQuestions();
    }

    public boolean isCorrectAnswer(Question question, String answer) {
        return question.getAnswerText().equalsIgnoreCase(answer);
    }

    public String getQuestionText(Question question) {
        return question.getQuestionText();
    }
}
