package com.CRUDOperation.Project.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.CRUDOperation.Project.model.BookDTO;
import com.CRUDOperation.Project.services.BookService;
import com.CRUDOperation.Project.entity.BookEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/library/")
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);
    @Autowired
    private BookService bookService;


    @GetMapping("books")
    public List<BookEntity> getAllBooks() {
        logger.info("Getting all books.");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookEntity getBookById(@PathVariable Integer id) {
        return bookService.getBooksById(id);
    }


    @GetMapping("/byName/{name}")
    public List<BookEntity> getBookByName(@PathVariable String name) {
        return bookService.getBooksByName(name);
    }

    @GetMapping("/byAuthor/{author}")
    public List<BookEntity> getBookByAuthor(@PathVariable String author) {
        return bookService.getBooksByAuthor(author);
    }


    @PostMapping
    public void saveBook(@Valid @RequestBody BookDTO book) {
        // used model and add it to entity class
        logger.info("saving the entered book in library");
        BookEntity newBook = new BookEntity();
        newBook.setAuthor(book.getAuthor());
        newBook.setName(book.getName());
        newBook.setPrice(book.getPrice());

        bookService.saveBook(newBook);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Integer id, @Valid @RequestBody Map<String, Object> updates) {
        logger.info("updating the book");

        BookEntity existBook = bookService.getBooksById(id);
        if (existBook != null) {
            bookService.updateBook(id, existBook, updates);
            return ResponseEntity.ok("Book updated successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Integer id) {
        BookEntity existBook = bookService.getBooksById(id);
        if (existBook != null) {
            bookService.deleteBook(id);
            return ResponseEntity.ok("Book deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book with ID " + id + " not found.");
        }
    }


}
