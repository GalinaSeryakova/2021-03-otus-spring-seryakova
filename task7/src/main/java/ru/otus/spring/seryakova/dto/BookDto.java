package ru.otus.spring.seryakova.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class BookDto {
    private long id;
    private String name;
    private AuthorDto author;
    private GenreDto genre;
}
