package ru.itrum.springData.task01.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Table("books")
public class Book {

    @Id
    private Long id;
    private String title;
    private String author;
    private Date publicationYear;

    public Book(String title, String author, Date publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}

