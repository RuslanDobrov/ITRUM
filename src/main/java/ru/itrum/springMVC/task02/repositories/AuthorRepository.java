package ru.itrum.springMVC.task02.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itrum.springMVC.task02.models.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
