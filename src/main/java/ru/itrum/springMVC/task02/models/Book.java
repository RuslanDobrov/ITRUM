package ru.itrum.springMVC.task02.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name = "Book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @NotNull
    @NotEmpty(message = "Book title must not be empty")
    @Column(name = "title")
    @Size(min = 1, max = 100, message = "Title must be between 1 and 100 characters long")
    private String title;

    @Column(name = "year")
    @Min(value = 1950, message = "Year of publication must be greater than 1950")
    private int year;

    @ManyToMany(mappedBy = "books")
    private List<Author> authors = new ArrayList<>();

    public Book(String title, int year) {
        this.title = title;
        this.year = year;
    }
}
