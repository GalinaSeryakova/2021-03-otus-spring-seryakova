package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.MessageSource;
import ru.otus.spring.seryakova.config.ApplicationSettings;

import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс LocalizationServiceImpl")
public class LocalizationServiceImplTest {

    @Mock
    private MessageSource messageSource;
    @Mock
    private ApplicationSettings settings;

    @DisplayName("должен возвращать строк из MessageSource")
    @Test
    void shouldReturnCorrectGreeting() {
        given(settings.getLocaleName()).willReturn("ru-RU");
        given(messageSource.getMessage(anyString(), any(), any(Locale.class))).willReturn("Hello, full name!");

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl(messageSource, settings);
        assertThat(localizationService.getMessage("key", "full name")).isEqualTo("Hello, full name!");
    }

    @DisplayName("должен возвращать имя файла")
    @Test
    void shouldReturnCorrectFileName() {
        given(settings.getLocaleName()).willReturn("ru-RU");
        given(settings.getQuestionsFile()).willReturn("file");

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl(messageSource, settings);
        assertThat(localizationService.getQuestionFile()).isEqualTo("file");
    }
}
