package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.dao.AuthorDao;
import ru.otus.spring.seryakova.domain.Author;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorDao authorDao;

    @Override
    public List<Author> findAll() {
        List<Author> authors = authorDao.getAll();
        if( authors == null || authors.isEmpty()){
            return new ArrayList<>();
        }
        return authors;
    }
}
