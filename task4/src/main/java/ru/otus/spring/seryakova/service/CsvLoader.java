package ru.otus.spring.seryakova.service;

import java.io.IOException;
import java.util.Collection;


public interface CsvLoader {

    <T> Collection<T> read(Class<T> clazz, String fileName) throws IOException;
}
