package ru.otus.spring.seryakova;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.spring.seryakova.service.QuestionService;

import java.io.IOException;

@Configuration
@ComponentScan
@PropertySource("classpath:application.properties")
public class Main {

    public static void main(String[] args) throws IOException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Main.class);

        QuestionService service = context.getBean(QuestionService.class);
        service.startQuiz();
        context.close();
    }
}
