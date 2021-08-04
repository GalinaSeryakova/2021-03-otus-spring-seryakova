package ru.otus.spring.seryakova.service;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.config.ApplicationSettings;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;
    private final String fileName;
    private final Locale locale;

    public LocalizationServiceImpl(MessageSource messageSource, ApplicationSettings settings) {
        this.messageSource = messageSource;
        this.fileName = settings.getQuestionsFile();
        this.locale = Locale.forLanguageTag(settings.getLocaleName());
    }

    @Override
    public String getMessage(String key, Object... parameters) {
        return messageSource.getMessage(key, parameters, locale);
    }

    @Override
    public String getQuestionFile() {
        return fileName;
    }
}
