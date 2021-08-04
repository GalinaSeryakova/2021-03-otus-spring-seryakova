package ru.otus.spring.seryakova.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Класс IOProviderServiceSystemImpl")
public class IOProviderServiceSystemImplTest {

    @Mock
    private PrintStream printStream;

    @DisplayName("должен выводить переданный текст")
    @Test
    public void shouldCorrectWrite() {
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        IOProviderServiceSystemImpl provider = new IOProviderServiceSystemImpl(System.in, printStream);
        provider.write("hello world");

        verify(printStream).println(captor.capture());
        assertEquals(captor.getValue(), "hello world");
    }

    @DisplayName("должен читать введенный текст")
    @Test
    public void shouldCorrectRead() {
        InputStream inputStream = new ByteArrayInputStream("answer".getBytes());

        IOProviderServiceSystemImpl provider = new IOProviderServiceSystemImpl(inputStream, System.out);
        String input = provider.read();
        assertEquals(input, "answer");
    }
}
