package ru.itrum.springMVC.task02.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itrum.springMVC.task02.models.Book;
import ru.itrum.springMVC.task02.repositories.BookRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Page<Book> findAll(Pageable pageable) {
        return bookRepository.findAll(pageable);
    }

    public Optional<Book> findBookById(int bookId) {
        return bookRepository.findById(bookId);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void delete(int bookId) {
        bookRepository.deleteById(bookId);
    }
}
