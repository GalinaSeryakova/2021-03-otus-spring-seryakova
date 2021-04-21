package ru.otus.spring.seryakova.service;

import org.springframework.stereotype.Component;

import java.io.InputStream;

@Component
public class ResourceFileLoader implements FileLoader {

    @Override
    public InputStream getAsStream(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
