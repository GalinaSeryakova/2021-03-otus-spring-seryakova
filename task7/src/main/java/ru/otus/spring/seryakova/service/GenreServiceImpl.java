package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.dto.GenreDto;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<GenreDto> findAll() {
        List<Genre> genres = genreRepository.findAll();
        if( genres == null || genres.isEmpty()){
            return new ArrayList<>();
        }
        return modelMapper.genreEntityListToDtoList(genres);
    }
}
