package com.CRUDOperation.Project.services;

import com.CRUDOperation.Project.repository.BookRepository;
import com.CRUDOperation.Project.entity.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookServiceImp implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<BookEntity> getAllBooks() {
        System.out.println("here");
        return bookRepository.findAll();
    }

    @Override
    public BookEntity getBooksById(Integer id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public List<BookEntity> getBooksByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public List<BookEntity> getBooksByAuthor(String author) {
        return bookRepository.findByAuthor(author);
    }

    @Override
    public void saveBook(BookEntity book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Integer id, BookEntity existBook, Map<String, Object> updates) {
        if (existBook != null) {
            if (updates.containsKey("author")) {
                existBook.setAuthor((String) updates.get("author"));
            }
            if (updates.containsKey("name")) {
                existBook.setName((String) updates.get("name"));
            }
            if (updates.containsKey("price")) {
                existBook.setPrice((Integer) updates.get("price"));
            }
            bookRepository.save(existBook);
        }

    }

    @Override
    public void deleteBook(Integer id) {
        bookRepository.deleteById(id);
    }


}
