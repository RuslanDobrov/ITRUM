package ru.itrum.springMVC.task02.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.itrum.springMVC.task02.models.Author;
import ru.itrum.springMVC.task02.models.Book;
import ru.itrum.springMVC.task02.services.AuthorService;
import ru.itrum.springMVC.task02.services.BookService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class Controller {
    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public Controller(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping()
    public ResponseEntity<Page<Book>> getAllBooks(Pageable pageable) {
        Page<Book> booksPage = bookService.findAll(pageable);
        return ResponseEntity.ok(booksPage);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookService.findAll());
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getBookById(@PathVariable int bookId) {
        Optional<Book> book = bookService.findBookById(bookId);
        if (book.isPresent()) {
            return ResponseEntity.ok().body(book.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody @Valid Book book) {
        if (book.getAuthors() != null) {
            for (Author author : book.getAuthors()) {
                Optional<Author> existingAuthor = authorService.findById(author.getId());
                if (existingAuthor.isPresent()) {
                    existingAuthor.get().getBooks().add(book);
                }
            }
        }
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<?> updateBook(@PathVariable int bookId, @RequestBody @Valid Book book) {
        Optional<Book> existingBook = bookService.findBookById(bookId);
        if (existingBook.isPresent()) {
            Book updatedBook = existingBook.get();
            updatedBook.setTitle(book.getTitle());
            updatedBook.setYear(book.getYear());
            updatedBook.setAuthors(book.getAuthors());
            bookService.save(updatedBook);
            return ResponseEntity.ok().body(updatedBook);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<?> deleteBook(@PathVariable int bookId) {
        Optional<Book> existingBook = bookService.findBookById(bookId);
        if (existingBook.isPresent()) {
            bookService.delete(bookId);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not Found");
        }
    }
}
