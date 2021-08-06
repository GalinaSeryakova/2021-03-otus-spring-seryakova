package ru.otus.spring.seryakova.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.seryakova.domain.Comment;
import ru.otus.spring.seryakova.dto.CommentDto;
import ru.otus.spring.seryakova.exception.NotFoundException;
import ru.otus.spring.seryakova.mapper.ModelMapper;
import ru.otus.spring.seryakova.repository.BookRepository;
import ru.otus.spring.seryakova.repository.CommentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final BookRepository bookRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    @Override
    public CommentDto addNewComment(CommentDto commentDto) {
        Comment comment = loadBookAndConvert(commentDto);
        return modelMapper.entityToDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentDto> findAllComments() {
        List<Comment> comments = commentRepository.findAll();
        if( comments == null || comments.isEmpty()){
            return new ArrayList<>();
        }
        return modelMapper.commentEntitiesToDtoList(comments);
    }

    @Override
    public CommentDto findCommentById(long id) {
        Optional<Comment> comment = commentRepository.findById(id);
        return comment.map(modelMapper::entityToDto).orElseThrow(() -> new NotFoundException("Comment with id " + id + " not found."));
    }

    @Override
    public void updateComment(CommentDto commentDto) {
        Comment comment = loadBookAndConvert(commentDto);
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(long id) {
        commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDto> findCommentsByBookId(long bookId) {
        List<Comment> comments = commentRepository.findCommentsByBookId(bookId);
        if( comments == null || comments.isEmpty()){
            return new ArrayList<>();
        }
        return modelMapper.commentEntitiesToDtoList(comments);
    }

    @Override
    public void deleteCommentByBookId(long bookId) {
        commentRepository.deleteCommentsByBookId(bookId);
    }

    private Comment loadBookAndConvert(CommentDto commentDto) {
        Comment comment = modelMapper.dtoToEntity(commentDto);
        if (comment.getBook() != null && comment.getBook().getId() != 0) {
            comment.setBook(bookRepository.findById(comment.getBook().getId())
                    .orElseThrow(() -> new NotFoundException("Book with id " + comment.getBook().getId() + " not found.")));
        }
        return comment;
    }
}
