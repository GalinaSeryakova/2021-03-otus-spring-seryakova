package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<AuthorDto> findAll() {
        List<Author> authors = authorRepository.findAll();
        if( authors == null || authors.isEmpty()){
            return new ArrayList<>();
        }
        return modelMapper.authorEntityListToDtoList(authors);
    }
}
