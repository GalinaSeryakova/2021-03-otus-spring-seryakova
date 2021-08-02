package ru.otus.spring.seryakova.shell;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Genre;
import ru.otus.spring.seryakova.service.AuthorService;
import ru.otus.spring.seryakova.service.BookService;
import ru.otus.spring.seryakova.service.GenreService;

import java.util.List;

@ShellComponent
public class ShellCommands {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;

    public ShellCommands(BookService bookService, AuthorService authorService, GenreService genreService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.genreService = genreService;
    }

    @ShellMethod(value = "Show all genres", key = {"g", "genres"})
    public void showAllGenres() {
        List<Genre> books = genreService.findAll();
        for (Genre book : books) {
            System.out.println(book.toString());
        }
    }

    @ShellMethod(value = "Show all authors", key = {"a", "helpauthors"})
    public void showAllAuthors() {
        List<Author> books = authorService.findAll();
        for (Author book : books) {
            System.out.println(book.toString());
        }
    }

    @ShellMethod(value = "Insert book", key = {"i", "insertBook"})
    public void insertBook(@ShellOption("name") String name, @ShellOption("authorId") Long authorId, @ShellOption("genreId") Long genreId) {
        Genre genre = new Genre(genreId, null);
        Author author = new Author(authorId, null);
        try {
            bookService.addNewBook(new Book(null, name, author, genre));
        } catch (DuplicateKeyException e) {
            System.out.println("Book identifier already exists");
        }
    }

    @ShellMethod(value = "Get book", key = {"b", "getBook"})
    public void getBook(@ShellOption("id") Long id) {
        Book book = bookService.findBookById(id);
        System.out.println(book.toString());
    }

    @ShellMethod(value = "List books", key = {"l", "listBooks"})
    public void listBooks() {
        List<Book> books = bookService.findAllBooks();
        for (Book book : books) {
            System.out.println(book.toString());
        }
    }

    @ShellMethod(value = "Update book", key = {"u", "updateBook"})
    public void updateBook(@ShellOption("id") Long id, @ShellOption("name") String name, @ShellOption("authorId") Long authorId, @ShellOption("genreId") Long genreId) {
        Genre genre = new Genre(genreId, null);
        Author author = new Author(authorId, null);
        bookService.updateBook(new Book(id, name, author, genre));
    }

    @ShellMethod(value = "Delete book", key = {"d", "deleteBook"})
    public void deleteBook(@ShellOption("id") Long id) {
        bookService.deleteBookById(id);
    }
}
