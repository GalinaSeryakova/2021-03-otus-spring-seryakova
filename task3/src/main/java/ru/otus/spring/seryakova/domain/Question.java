package ru.otus.spring.seryakova.domain;

public class Question {

    private String questionText;
    private String answerText;

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getAnswerText() {
        return answerText;
    }

    public void setAnswerText(String answerText) {
        this.answerText = answerText;
    }

    @Override
    public String toString() {
        return "Question: " + questionText + "\n" +
                "Answer: " + answerText + "\n";
    }
}
