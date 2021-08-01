package ru.otus.spring.seryakova.dao;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import ru.otus.spring.seryakova.domain.Question;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false"
})
@DisplayName("Класс QuestionDaoCSV")
public class QuestionDaoIntegrationTest {

    @Autowired
    private QuestionDao dao;

    @DisplayName("возвращает все вопросы")
    @Test
    public void test() {
        Collection<Question> questions = dao.getQuestions();
        assertNotNull(questions);
        assertEquals(2, questions.size());
    }
}
