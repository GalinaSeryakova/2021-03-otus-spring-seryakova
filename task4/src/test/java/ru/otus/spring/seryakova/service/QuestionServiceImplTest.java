package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.dao.QuestionDao;
import ru.otus.spring.seryakova.domain.Question;

import java.util.Arrays;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс QuestionServiceImpl")
public class QuestionServiceImplTest {

    @Mock
    private QuestionDao dao;
    @InjectMocks
    private QuestionServiceImpl questionService;

    @DisplayName("возвращает все вопросы")
    @Test
    void shouldReturnAllQuestions() {
        given(dao.getQuestions())
                .willReturn(Arrays.asList(
                        getQuestion("text1", "answer1"),
                        getQuestion("text2", "answer2")));

        Collection<Question> questions = questionService.getQuestions();

        assertNotNull(questions);
        assertEquals(2, questions.size());
        verify(dao, times(1)).getQuestions();

    }

    @DisplayName("корректно проверяет правильность ответа")
    @Test
    void shouldCorrectCheckAnswer() {
        assertTrue(questionService.isCorrectAnswer(getQuestion("text1", "answer1"), "answer1"));
        assertFalse(questionService.isCorrectAnswer(getQuestion("text1", "answer1"), "answer2"));
    }

    @DisplayName("корректно формирует текст вопроса")
    @Test
    void shouldCorrectFormatQuestionText() {
        assertThat(questionService.getQuestionText(getQuestion("text1", "answer1"))).isEqualTo("text1");
    }

    private Question getQuestion(String text, String answer) {
        Question question = new Question();
        question.setQuestionText(text);
        question.setAnswerText(answer);
        return question;
    }
}
