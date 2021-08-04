package ru.otus.spring.seryakova.shell;

import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import ru.otus.spring.seryakova.service.LocalizationService;
import ru.otus.spring.seryakova.service.QuizService;

@ShellComponent
public class QuizShell {

    private String personName;
    private final QuizService service;
    private final LocalizationService localizationService;

    public QuizShell(QuizService service, LocalizationService localizationService) {
        this.service = service;
        this.localizationService = localizationService;
    }

    @ShellMethod(value = "Регистрация студента", key = {"r", "register"})
    public String askName() {
        // invoke service
        this.personName = service.askName();
        return localizationService.getMessage("shell.register.success");
    }

    @ShellMethod(value = "Запуск тестирования", key = {"t", "test"})
    @ShellMethodAvailability(value = "isRegistered")
    public String askQuestions() {
        // invoke service
        service.askQuestions();
        return localizationService.getMessage("shell.quiz.finished");
    }

    private Availability isRegistered() {
        return this.personName == null
                ? Availability.unavailable(localizationService.getMessage("shell.not.registered"))
                : Availability.available();
    }
}
