package ru.otus.spring.seryakova.service;

import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class IOProviderServiceSystemImpl implements IOProviderService {

    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void write(String text) {
        System.out.println(text);
    }

    @Override
    public String read() {
        return scanner.next();
    }
}
