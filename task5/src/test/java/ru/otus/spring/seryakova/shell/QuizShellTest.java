package ru.otus.spring.seryakova.shell;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.Shell;
import ru.otus.spring.seryakova.service.LocalizationService;
import ru.otus.spring.seryakova.service.QuizService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("тест команд shell")
@SpringBootTest
public class QuizShellTest {

    @MockBean
    private QuizService quizService;

    @Autowired
    private Shell shell;

    @Autowired
    private LocalizationService localizationService;

    private static final String COMMAND_REGISTER = "register";
    private static final String COMMAND_REGISTER_SHORT = "r";
    private static final String GREETING_REGISTER =
            "Поздравляем. Вы зарегистрированы. Теперь Вы можете пройти тест нажав: t (test).";
    private static final String COMMAND_TEST = "test";
    private static final String COMMAND_TEST_SHORT = "t";

    @DisplayName("должен возвращать форму приветствия после регистрации пользователя")
    @Test
    void mustReturnTheWelcomeAfterUserRegistration() {
        String res = (String) shell.evaluate(() -> COMMAND_REGISTER);
        assertEquals(GREETING_REGISTER, res);

        res = (String) shell.evaluate(() -> COMMAND_REGISTER_SHORT);
        assertEquals(GREETING_REGISTER, res);
    }

    @DisplayName("должен возвращать ошибку, т.к. пользователь не зарегистрирован")
    @Test
    void mustReturnErrorWithoutRegistration() {
        Object res = shell.evaluate(() -> COMMAND_TEST);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);

        res = shell.evaluate(() -> COMMAND_TEST_SHORT);
        assertThat(res).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

}
