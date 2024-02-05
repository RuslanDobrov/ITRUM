package ru.itrum.springData.task01.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Table("books")
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private LocalDate publicationYear;

    public Book(Long id, String title, String author, LocalDate publicationYear) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}

