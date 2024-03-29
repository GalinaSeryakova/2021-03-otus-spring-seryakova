package ru.otus.spring.seryakova.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("application")
public class ApplicationSettings {
    private String version;
    private String localeName;
    private String questionsFile;
}
