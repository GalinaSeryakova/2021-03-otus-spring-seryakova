package ru.otus.spring.seryakova.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.domain.Question;

import java.util.Collection;

@Service
public class QuizService {

    private final QuestionService questionService;
    private final IOProviderService ioService;

    public QuizService(QuestionService questionService, IOProviderService ioService) {
        this.questionService = questionService;
        this.ioService = ioService;
    }

    public void startQuiz() {
        String fullName = askName();
        ioService.write(getHello(fullName));
        askQuestions();
    }

    public String askName() {
        ioService.write("Enter your full name:");
        return ioService.read();
    }

    public String getHello(String fullName) {
         return new StringBuffer("Hello, ").append(fullName).append("!").toString();
    }

    public void askQuestions() {
        Collection<Question> questions = questionService.getQuestions();
        int correct = 0;

        ioService.write(new StringBuffer("There are ").append(questions.size()).append(" questions.").toString());
        for (Question question : questions) {
            if (askAndCheckQuestion(question)) {
                correct++;
            }
        }

        ioService.write(getResult(correct, questions.size()));
    }

    public boolean askAndCheckQuestion(Question question) {
        ioService.write(questionService.getQuestionText(question));
        String answer = ioService.read();
        return questionService.isCorrectAnswer(question, answer);
    }

    public String getResult(int correct, int count) {
        return new StringBuffer("Your result is ").append(correct)
                .append(" correct answers from ").append(count).append(" questions").toString();
    }
}
