package ru.otus.spring.seryakova.exception;

public class QuestionsLoadingException extends RuntimeException {

    public QuestionsLoadingException(String message) {
        super(message);
    }

    public QuestionsLoadingException(String message, Throwable cause) {
        super(message, cause);
    }
}
