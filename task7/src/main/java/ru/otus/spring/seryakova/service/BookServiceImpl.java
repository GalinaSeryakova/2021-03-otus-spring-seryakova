package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.dto.BookDto;
import ru.otus.spring.seryakova.exception.NotFoundException;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.AuthorRepository;
import ru.otus.spring.seryakova.repository.BookRepository;
import ru.otus.spring.seryakova.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final ModelMapper modelMapper;

    @Transactional
    @Override
    public BookDto addNewBook(BookDto bookDto) {
        Book book = loadAuthorAndGenreAndConvert(bookDto);
        return modelMapper.entityToDto(bookRepository.save(book));
    }

    @Override
    public List<BookDto> findAllBooks() {
        List<Book> books = bookRepository.findAll();
        if( books == null || books.isEmpty()){
            return new ArrayList<>();
        }
        return modelMapper.bookEntityListToDtoList(books);
    }

    @Override
    public BookDto findBookById(long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(modelMapper::entityToDto).orElseThrow(() -> new NotFoundException("Book with id " + id + " not found."));
    }

    @Transactional
    @Override
    public void updateBook(BookDto bookDto) {
        Book book = loadAuthorAndGenreAndConvert(bookDto);
        bookRepository.save(book);
    }

    @Transactional
    @Override
    public void deleteBookById(long id) {
        bookRepository.deleteById(id);
    }

    private Book loadAuthorAndGenreAndConvert(BookDto bookDto) {
        Book book = modelMapper.dtoToEntity(bookDto);
        if (book.getAuthor() != null && book.getAuthor().getId() != 0) {
            book.setAuthor(authorRepository.findById(book.getAuthor().getId())
                    .orElseThrow(() -> new NotFoundException("Author with id " + book.getAuthor().getId() + " not found.")));
        }
        if (book.getGenre() != null && book.getGenre().getId() != 0) {
            book.setGenre(genreRepository.findById(book.getGenre().getId())
                    .orElseThrow(() -> new NotFoundException("Genre with id " + book.getGenre().getId() + " not found.")));
        }
        return book;
    }

}
