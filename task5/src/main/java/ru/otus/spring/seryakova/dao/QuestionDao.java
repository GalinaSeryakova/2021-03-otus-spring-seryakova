package ru.otus.spring.seryakova.dao;

import ru.otus.spring.seryakova.domain.Question;

import java.util.Collection;

public interface QuestionDao {

    Collection<Question> getQuestions();
}
