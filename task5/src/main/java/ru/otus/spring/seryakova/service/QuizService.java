package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.domain.Question;

public interface QuizService {
    void startQuiz() ;

    String askName();

    void askQuestions();

    boolean askAndCheckQuestion(Question question);
}
