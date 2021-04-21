package ru.otus.spring.seryakova.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;


public interface CsvReader {

    <T> Collection<T> read(Class<T> clazz, InputStream inputStream) throws IOException;
}
