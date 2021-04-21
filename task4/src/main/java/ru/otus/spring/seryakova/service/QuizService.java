package ru.otus.spring.seryakova.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.domain.Question;

import java.util.Collection;

@Service
@AllArgsConstructor
public class QuizService {

    private final QuestionService questionService;
    private final IOProviderService ioService;
    private final LocalizationService localizationService;

    public void startQuiz() {
        String fullName = askName();
        ioService.write(localizationService.getMessage("strings.hello", fullName));
        askQuestions();
    }

    public String askName() {
        ioService.write(localizationService.getMessage("strings.enter.full.name"));
        return ioService.read();
    }

    public void askQuestions() {
        Collection<Question> questions = questionService.getQuestions();
        int correct = 0;

        ioService.write(localizationService.getMessage("strings.questions.count", questions.size()));
        for (Question question : questions) {
            if (askAndCheckQuestion(question)) {
                correct++;
            }
        }

        ioService.write(localizationService.getMessage("strings.quiz.result", correct, questions.size()));
    }

    public boolean askAndCheckQuestion(Question question) {
        ioService.write(questionService.getQuestionText(question));
        String answer = ioService.read();
        return questionService.isCorrectAnswer(question, answer);
    }
}
