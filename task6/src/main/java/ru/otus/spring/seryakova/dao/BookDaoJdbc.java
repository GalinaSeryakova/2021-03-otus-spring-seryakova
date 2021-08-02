package ru.otus.spring.seryakova.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.otus.spring.seryakova.domain.Author;
import ru.otus.spring.seryakova.domain.Book;
import ru.otus.spring.seryakova.domain.Genre;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    @Override
    public long insert(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", book.getName());
        params.addValue("author_id", book.getAuthor().getId());
        params.addValue("genre_id", book.getGenre().getId());
        KeyHolder kh = new GeneratedKeyHolder();
        namedParameterJdbcOperations.update("insert into BOOKS (NAME, AUTHOR_ID, GENRE_ID) values (:name, :author_id, :genre_id)", params, kh);
        return kh.getKey().longValue();
    }

    @Override
    public void update(Book book) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", book.getId());
        if (book.getName() != null) {
            params.put("name", book.getName());
        }
        if (book.getAuthor() != null) {
            params.put("author_id", book.getAuthor().getId());
        }
        if (book.getGenre() != null) {
            params.put("genre_id", book.getGenre().getId());
        }
        namedParameterJdbcOperations.update(
                "update BOOKS set ID = :id, NAME = :name, AUTHOR_ID = :author_id, GENRE_ID = :genre_id where ID = :id", params);
    }

    @Override
    public Book getById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select b.ID, b.NAME, " +
                            "b.AUTHOR_ID, a.NAME as AUTHOR_NAME, " +
                            "b.GENRE_ID, g.NAME as GENRE_NAME " +
                            "from BOOKS b " +
                            "inner join AUTHORS a on a.ID = b.AUTHOR_ID " +
                            "inner join GENRES g on g.ID = b.GENRE_ID " +
                            "where b.ID = :id", params,
                    new BookMapper()
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<Book> getAll() {
        return namedParameterJdbcOperations.query(
                "select b.ID, b.NAME, " +
                        "b.AUTHOR_ID, a.NAME as AUTHOR_NAME, " +
                        "b.GENRE_ID, g.NAME as GENRE_NAME " +
                        "from BOOKS b " +
                        "inner join AUTHORS a on a.ID = b.AUTHOR_ID " +
                        "inner join GENRES g on g.ID = b.GENRE_ID",
                new BookMapper());
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from BOOKS where ID = :id", params
        );
    }

    private static class BookMapper implements RowMapper<Book> {
        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book(
                    resultSet.getLong("ID"),
                    resultSet.getString("NAME"),
                    new Author(resultSet.getLong("AUTHOR_ID"),
                            resultSet.getString("AUTHOR_NAME")),
                    new Genre(resultSet.getLong("GENRE_ID"),
                            resultSet.getString("GENRE_NAME")));
        }
    }
}
