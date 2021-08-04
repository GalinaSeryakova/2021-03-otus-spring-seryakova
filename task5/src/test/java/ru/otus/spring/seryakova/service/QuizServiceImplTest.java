package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.domain.Question;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс QuizService")
public class QuizServiceImplTest {

    @Mock
    private QuestionService questionService;
    @Mock
    private IOProviderService ioService;
    @Mock
    private LocalizationService localizationService;
    @InjectMocks
    private QuizServiceImpl quizServiceImpl;

    @DisplayName("должен читать имя")
    @Test
    void shouldAskName() {
        given(ioService.read()).willReturn("full name");
        assertThat(quizServiceImpl.askName()).isEqualTo("full name");
    }

    @DisplayName("должен задавать все вопросы")
    @Test
    void shouldAskAllQuestions() {
        given(questionService.getQuestions())
                .willReturn(Arrays.asList(
                        getQuestion("text1", "answer1"),
                        getQuestion("text2", "answer2")));

        quizServiceImpl.askQuestions();

        verify(questionService, times(1)).getQuestions();
        verify(ioService, times(2)).read();
    }

    @DisplayName("должен корректно проверять ответ")
    @Test
    void shouldAskAndCheckQuestionWithCorrectAnswer() {
        given(ioService.read()).willReturn("answer1");
        given(questionService.isCorrectAnswer(notNull(Question.class), anyString())).willReturn(true);
        assertTrue(quizServiceImpl.askAndCheckQuestion(getQuestion("text1", "answer1")));
    }

    private Question getQuestion(String text, String answer) {
        Question question = new Question();
        question.setQuestionText(text);
        question.setAnswerText(answer);
        return question;
    }
}
