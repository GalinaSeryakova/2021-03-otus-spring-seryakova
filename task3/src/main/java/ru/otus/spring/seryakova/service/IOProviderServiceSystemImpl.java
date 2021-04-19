package ru.otus.spring.seryakova.service;

import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.Scanner;

@Service
public class IOProviderServiceSystemImpl implements IOProviderService {

    private PrintStream printStream;
    private Scanner scanner;

    public IOProviderServiceSystemImpl() {
        this.printStream = System.out;
        this.scanner = new Scanner(System.in);
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
