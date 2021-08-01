package ru.otus.spring.seryakova.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.seryakova.domain.Question;
import ru.otus.spring.seryakova.service.CsvReader;
import ru.otus.spring.seryakova.service.FileLoader;
import ru.otus.spring.seryakova.service.QuizService;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
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
    private FileLoader loader;

    @Mock
    private CsvReader reader;

    @DisplayName("возвращает все вопросы")
    @Test
    void shouldReturnAllQuestions() throws IOException {
        given(loader.getAsStream(anyString()))
                .willReturn(new ByteArrayInputStream("questionText,answerText\n2+2,4\n2-2,0".getBytes()));
        given(reader.read(eq(Question.class), any(InputStream.class)))
                .willReturn(Arrays.asList(
                        getQuestion("text1", "answer1"),
                        getQuestion("text2", "answer2")));

        QuestionDaoCSV questionDao = new QuestionDaoCSV("file", loader, reader);
        Collection<Question> questions = questionDao.getQuestions();

        assertNotNull(questions);
        assertEquals(2, questions.size());
        verify(loader, times(1)).getAsStream(anyString());
        verify(reader, times(1)).read(eq(Question.class), any(InputStream.class));

    }

    private Question getQuestion(String text, String answer) {
        Question question = new Question();
        question.setQuestionText(text);
        question.setAnswerText(answer);
        return question;
    }
}
