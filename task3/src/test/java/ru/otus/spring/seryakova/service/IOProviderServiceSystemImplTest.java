package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс IOProviderServiceSystemImpl")
public class IOProviderServiceSystemImplTest {

    @Spy
    private PrintStream printStream = new PrintStream(new ByteArrayOutputStream());

    @DisplayName("должен выводить переданный текст")
    @Test
    public void shouldCorrectWrite() {
        System.setOut(printStream);

        IOProviderServiceSystemImpl provider = new IOProviderServiceSystemImpl();
        provider.write("hello world");

        verify(printStream).println("hello world");
    }

    @DisplayName("должен читать введенный текст")
    @Test
    public void shouldCorrectRead() {
        InputStream inputStream = new ByteArrayInputStream("answer".getBytes());
        System.setIn(inputStream);

        IOProviderServiceSystemImpl provider = new IOProviderServiceSystemImpl();
        String input = provider.read();
        assertEquals(input, "answer");
    }
}
