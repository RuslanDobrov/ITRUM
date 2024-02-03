package ru.itrum.springMVC.task02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springMVC.task02.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {
}
