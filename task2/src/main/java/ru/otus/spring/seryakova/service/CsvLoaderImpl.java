package ru.otus.spring.seryakova.service;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

@Component
public class CsvLoaderImpl implements CsvLoader {

    private static final CsvMapper csvMapper = new CsvMapper();

    public <T> Collection<T> read(Class<T> clazz, InputStream inputStream) throws IOException {
        CsvSchema schema = csvMapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectReader reader = csvMapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(inputStream).readAll();
    }
}
