package ru.otus.spring.seryakova.repository;

import ru.otus.spring.seryakova.domain.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository {
    Comment save(Comment comment);

    Optional<Comment> findById(long id);

    List<Comment> findAll();

    void deleteById(long id);

    void deleteCommentsByBookId(long bookId);

    List<Comment> findCommentsByBookId(long bookId);
}
