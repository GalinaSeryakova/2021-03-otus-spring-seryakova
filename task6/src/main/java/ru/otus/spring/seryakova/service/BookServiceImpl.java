package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.spring.seryakova.dao.AuthorDao;
import ru.otus.spring.seryakova.dao.BookDao;
import ru.otus.spring.seryakova.dao.GenreDao;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Genre;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;


    @Override
    public long addNewBook(Book book) {
        Author author = null;
        Genre genre = null;
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            author = authorDao.getById(book.getAuthor().getId());
        }
        if (book.getGenre() != null && book.getGenre().getId() != null) {
            genre = genreDao.getById(book.getGenre().getId());
        }
        return bookDao.insert(new Book(null, book.getName(), author, genre));
    }

    @Override
    public List<Book> findAllBooks() {
        List<Book> listOfBooks = bookDao.getAll();
        if (listOfBooks == null) {
            return new ArrayList<>();
        }
        return listOfBooks;
    }

    @Override
    public Book findBookById(long id) {
        return bookDao.getById(id);
    }

    @Override
    public void updateBook(Book book) {
        Author author = null;
        Genre genre = null;
        if (book.getAuthor() != null && book.getAuthor().getId() != null) {
            author = authorDao.getById(book.getAuthor().getId());
        }
        if (book.getGenre() != null && book.getGenre().getId() != null) {
            genre = genreDao.getById(book.getGenre().getId());
        }
        bookDao.update(new Book(book.getId(), book.getName(), author, genre));
    }

    @Override
    public void deleteBookById(long id) {
        bookDao.deleteById(id);
    }

}
