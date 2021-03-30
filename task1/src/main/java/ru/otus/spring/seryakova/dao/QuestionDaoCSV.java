package ru.otus.spring.seryakova.dao;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import ru.otus.spring.seryakova.domain.Question;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class QuestionDaoCSV implements QuestionDao {

    private final String fileName;

    public QuestionDaoCSV(String fileName) {
        this.fileName = fileName;
    }

    public Collection<Question> getQuestions() throws IOException {
        return loadQuestions();
    }

    private Collection<Question> loadQuestions() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        CsvMapper csvMapper = new CsvMapper();
        CsvSchema schema = csvMapper.schemaFor(Question.class).withHeader().withColumnReordering(true);

        ObjectReader reader = csvMapper.readerFor(Question.class).with(schema);
        return reader.<Question>readValues(inputStream).readAll();
    }
}
