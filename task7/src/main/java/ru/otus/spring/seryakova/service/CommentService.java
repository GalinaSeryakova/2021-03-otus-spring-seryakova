package ru.otus.spring.seryakova.service;

import ru.otus.spring.seryakova.dto.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto addNewComment(CommentDto commentDto);

    List<CommentDto> findAllComments();

    CommentDto findCommentById(long id);

    void updateComment(CommentDto commentDto);

    void deleteCommentById(long id);

    List<CommentDto> findCommentsByBookId(long bookId);

    void deleteCommentByBookId(long bookId);
}
