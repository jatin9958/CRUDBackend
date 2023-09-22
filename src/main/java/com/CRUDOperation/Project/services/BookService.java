package com.CRUDOperation.Project.services;

import com.CRUDOperation.Project.entity.BookEntity;

import java.util.List;
import java.util.Map;

public interface BookService {
    List<BookEntity> getAllBooks();

    BookEntity getBooksById(Integer id);

    List<BookEntity> getBooksByName(String name);

    List<BookEntity> getBooksByAuthor(String author);
    void saveBook(BookEntity book);

    void deleteBook(Integer id);

    void updateBook(Integer id, BookEntity existBook, Map<String, Object> updates);

}
