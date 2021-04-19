package ru.otus.spring.seryakova.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Question")
public class QuestionTest {

    @DisplayName("должен корректно преобразовываться в строку")
    @Test
    void shouldHaveCorrectToString() {
        Question question = new Question();

        question.setQuestionText("question");
        question.setAnswerText("answer");

        assertThat(question.toString()).isEqualTo("Question: question\nAnswer: answer\n");
    }
}
