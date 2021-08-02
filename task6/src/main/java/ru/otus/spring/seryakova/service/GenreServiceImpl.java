package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.dao.GenreDao;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public List<Genre> findAll() {
        List<Genre> genres = genreDao.getAll();
        if( genres == null || genres.isEmpty()){
            return new ArrayList<>();
        }
        return genres;
    }
}
