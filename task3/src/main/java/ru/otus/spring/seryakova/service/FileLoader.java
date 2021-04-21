package ru.otus.spring.seryakova.service;

import java.io.InputStream;

public interface FileLoader {
    InputStream getAsStream(String fileName);
}
