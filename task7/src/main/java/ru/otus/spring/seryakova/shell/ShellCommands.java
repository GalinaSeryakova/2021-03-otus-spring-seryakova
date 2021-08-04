package ru.otus.spring.seryakova.shell;

import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.spring.seryakova.dto.AuthorDto;
import ru.otus.spring.seryakova.dto.BookDto;
import ru.otus.spring.seryakova.dto.CommentDto;
import ru.otus.spring.seryakova.dto.GenreDto;
import ru.otus.spring.seryakova.service.AuthorService;
import ru.otus.spring.seryakova.service.BookService;
import ru.otus.spring.seryakova.service.CommentService;
import ru.otus.spring.seryakova.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor
public class ShellCommands {
    private final BookService bookService;
    private final AuthorService authorService;
    private final GenreService genreService;
    private final CommentService commentService;

    @ShellMethod(value = "Show all genres", key = {"g", "genres"})
    public void showAllGenres() {
        List<GenreDto> genres = genreService.findAll();
        for (GenreDto genre : genres) {
            System.out.println(genre);
        }
    }

    @ShellMethod(value = "Show all authors", key = {"a", "authors"})
    public void showAllAuthors() {
        List<AuthorDto> authors = authorService.findAll();
        for (AuthorDto author : authors) {
            System.out.println(author);
        }
    }

    @ShellMethod(value = "Insert book", key = {"i", "insertBook"})
    public void insertBook(@ShellOption("name") String name, @ShellOption("authorId") Long authorId, @ShellOption("genreId") Long genreId) {
        GenreDto genre = new GenreDto(genreId, null);
        AuthorDto author = new AuthorDto(authorId, null);
        bookService.addNewBook(new BookDto(0, name, author, genre));
    }

    @ShellMethod(value = "Get book", key = {"b", "getBook"})
    public void getBook(@ShellOption("id") Long id) {
        BookDto book = bookService.findBookById(id);
        System.out.println(book);
    }

    @ShellMethod(value = "List books", key = {"l", "listBooks"})
    public void listBooks() {
        List<BookDto> books = bookService.findAllBooks();
        for (BookDto book : books) {
            System.out.println(book);
        }
    }

    @ShellMethod(value = "Update book", key = {"u", "updateBook"})
    public void updateBook(@ShellOption("id") Long id, @ShellOption("name") String name, @ShellOption("authorId") Long authorId, @ShellOption("genreId") Long genreId) {
        GenreDto genre = new GenreDto(genreId, null);
        AuthorDto author = new AuthorDto(authorId, null);
        bookService.updateBook(new BookDto(id, name, author, genre));
    }

    @ShellMethod(value = "Delete book", key = {"d", "deleteBook"})
    public void deleteBook(@ShellOption("id") Long id) {
        bookService.deleteBookById(id);
    }

    @ShellMethod(value = "Add comment to book", key = {"ac", "addComment"})
    public void addComment(@ShellOption("text") String text, @ShellOption("bookId") Long bookId) {
        BookDto book = new BookDto(bookId, null, null, null);
        commentService.addNewComment(new CommentDto(0, text, book));
    }

    @ShellMethod(value = "Get comment", key = {"gc", "getComment"})
    public void getComment(@ShellOption("id") Long id) {
        CommentDto comment = commentService.findCommentById(id);
        System.out.println(comment);
    }

    @ShellMethod(value = "List comments", key = {"lc", "listComments"})
    public void listComments() {
        List<CommentDto> comments = commentService.findAllComments();
        for (CommentDto comment : comments) {
            System.out.println(comment);
        }
    }

    @ShellMethod(value = "Update comment", key = {"uc", "updateComment"})
    public void updateComment(@ShellOption("id") Long id, @ShellOption("text") String text, @ShellOption("bookId") Long bookId) {
        BookDto book = new BookDto(bookId, null, null, null);
        commentService.updateComment(new CommentDto(id, text, book));
    }

    @ShellMethod(value = "Delete comment", key = {"dc", "deleteComment"})
    public void deleteComment(@ShellOption("id") Long id) {
        commentService.deleteCommentById(id);
    }

    @ShellMethod(value = "List book comments", key = {"lbc", "listBookComments"})
    public void listBookComments(@ShellOption("bookId") Long bookId) {
        List<CommentDto> comments = commentService.findCommentsByBookId(bookId);
        for (CommentDto comment : comments) {
            System.out.println(comment);
        }
    }

    @ShellMethod(value = "Delete book comment", key = {"dbc", "deleteBookComment"})
    public void deleteBookComment(@ShellOption("bookId") Long bookId) {
        commentService.deleteCommentByBookId(bookId);
    }
}
