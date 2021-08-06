package ru.otus.spring.seryakova.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.otus.spring.seryakova.domain.Comment;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class CommentRepositoryJpa implements CommentRepository {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public Comment save(Comment comment) {
        if (comment.getId() == 0) {
            entityManager.persist(comment);
            return comment;
        } else {
            return entityManager.merge(comment);
        }
    }

    @Override
    public Optional<Comment> findById(long id) {
        return Optional.ofNullable(entityManager.find(Comment.class, id));
    }

    @Override
    public List<Comment> findAll() {
        EntityGraph<?> graph = entityManager.getEntityGraph(Comment.WITH_BOOK_GRAPH);
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c", Comment.class);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {
        Query query = entityManager.createQuery("delete from Comment c where c.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


    @Override
    public void deleteCommentsByBookId(long bookId) {
        Query query = entityManager.createQuery("delete from Comment c where c.book.id = :bookId");
        query.setParameter("bookId", bookId);
        query.executeUpdate();
    }

    @Override
    public List<Comment> findCommentsByBookId(long bookId) {
        EntityGraph<?> graph = entityManager.getEntityGraph(Comment.WITH_BOOK_GRAPH);
        TypedQuery<Comment> query = entityManager.createQuery("select c from Comment c where c.book.id =: bookId",
                Comment.class);
        query.setParameter("bookId", bookId);
        query.setHint("javax.persistence.fetchgraph", graph);
        return query.getResultList();
    }
}
