package ru.otus.spring.seryakova.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.dao.QuestionDao;
import ru.otus.spring.seryakova.domain.Question;

import java.io.IOException;
import java.util.Collection;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;
    private final IOProviderService ioService;

    @Autowired
    public QuestionServiceImpl(QuestionDao dao, IOProviderService ioService) {
        this.dao = dao;
        this.ioService = ioService;
    }

    @Override
    public void startQuiz() throws IOException {
        String fullName = askName();
        ioService.write(new StringBuffer("Hello, ").append(fullName).append("!").toString());

        Collection<Question> questions = dao.getQuestions();
        int correct = 0;

        ioService.write(new StringBuffer("There are ").append(questions.size()).append(" questions.").toString());
        for (Question question : questions) {
            if (askAndCheckQuestion(question)) {
                correct++;
            }
        }

        ioService.write(new StringBuffer("Your result is ").append(correct)
                .append(" correct answers from ").append(questions.size()).append(" questions").toString());
    }

    private String askName() {
        ioService.write("Enter your full name:");
        return ioService.read();
    }

    private boolean askAndCheckQuestion(Question question) {
        ioService.write(question.getQuestionText());
        String answer = ioService.read();
        return question.getAnswerText().equalsIgnoreCase(answer);
    }
}
