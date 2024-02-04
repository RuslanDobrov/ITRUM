package ru.itrum.springData.task01.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itrum.springData.task01.model.Book;
import ru.itrum.springData.task01.repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public void save(Book updatedBook) {
        bookRepository.save(updatedBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
