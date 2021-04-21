package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.domain.Question;

import java.util.Collection;

public interface QuestionService {

    Collection<Question> getQuestions();

    boolean isCorrectAnswer(Question question, String answer);

    String getQuestionText(Question question);
}
