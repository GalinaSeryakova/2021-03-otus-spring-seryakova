package ru.otus.spring.seryakova.service;

public interface LocalizationService {

    String getMessage(String key, Object... parameters);

    String getQuestionFile();
}
