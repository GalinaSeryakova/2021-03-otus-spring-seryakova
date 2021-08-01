package ru.otus.spring.seryakova.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.service.CsvLoader;
import ru.otus.spring.seryakova.service.LocalizationService;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс QuestionDaoCSV")
public class QuestionDaoCSVTest {

    @Mock
    private CsvLoader loader;

    @Mock
    private LocalizationService localizationService;

    @DisplayName("возвращает все вопросы")
    @Test
    void shouldReturnAllQuestions() throws IOException {
        given(loader.read(eq(Question.class), anyString()))
                .willReturn(Arrays.asList(
                        getQuestion("text1", "answer1"),
                        getQuestion("text2", "answer2")));
        given(localizationService.getQuestionFile()).willReturn("fileName");

        QuestionDaoCSV questionDao = new QuestionDaoCSV(localizationService, loader);
        Collection<Question> questions = questionDao.getQuestions();

        assertNotNull(questions);
        assertEquals(2, questions.size());
        verify(loader, times(1)).read(eq(Question.class), anyString());

    }

    private Question getQuestion(String text, String answer) {
        Question question = new Question();
        question.setQuestionText(text);
        question.setAnswerText(answer);
        return question;
    }
}
