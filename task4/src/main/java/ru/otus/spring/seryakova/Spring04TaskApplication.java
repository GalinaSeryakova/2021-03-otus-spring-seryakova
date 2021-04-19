package ru.otus.spring.seryakova;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.otus.spring.seryakova.service.QuizService;

@SpringBootApplication
public class Spring04TaskApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Spring04TaskApplication.class, args);
        QuizService service = context.getBean(QuizService.class);
        service.startQuiz();
    }

}
