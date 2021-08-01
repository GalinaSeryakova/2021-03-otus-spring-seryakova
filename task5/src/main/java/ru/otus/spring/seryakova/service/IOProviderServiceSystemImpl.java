package ru.otus.spring.seryakova.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOProviderServiceSystemImpl implements IOProviderService {

    private PrintStream printStream;
    private Scanner scanner;

    public IOProviderServiceSystemImpl(@Value("#{ T(java.lang.System).in}") InputStream in,
                                  @Value("#{ T(java.lang.System).out}") PrintStream out) {
        this.printStream = out;
        this.scanner = new Scanner(in);
    }

    @Override
    public void write(String text) {
        printStream.println(text);
    }

    @Override
    public String read() {
        return scanner.next();
    }
}
