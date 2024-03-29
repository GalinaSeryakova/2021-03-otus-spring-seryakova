package ru.otus.spring.seryakova;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.seryakova.service.QuestionService;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");
        QuestionService service = context.getBean(QuestionService.class);
        service.printQuestions();
        context.close();
    }
}
