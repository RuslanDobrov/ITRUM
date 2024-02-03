package ru.itrum.springMVC.task02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itrum.springMVC.task02.models.Author;
import ru.itrum.springMVC.task02.repositories.AuthorRepository;
import java.util.Optional;

@Service
public class AuthorService {
    public AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Optional<Author> findById(int id) {
        return authorRepository.findById(id);
    }
}
