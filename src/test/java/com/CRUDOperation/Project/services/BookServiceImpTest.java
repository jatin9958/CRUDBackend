package com.CRUDOperation.Project.services;

import com.CRUDOperation.Project.entity.BookEntity;
import com.CRUDOperation.Project.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BookServiceImpTest {

    @Mock
    private BookRepository bookRepository;

    private BookServiceImp bookServiceImp;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookServiceImp = new BookServiceImp(bookRepository);
    }

    @Test
    public void testGetAllBook() {

        List<BookEntity> expectedBooks = Arrays.asList(new BookEntity(1, "Travis", "Book1", 109), new BookEntity(2, "Ronaldo", "Book2", 200));

        List<BookEntity> saved = bookRepository.saveAll(expectedBooks);

        // mock scenerio to return this thing when calling
        when(bookRepository.findAll()).thenReturn(saved);


        List<BookEntity> books = bookServiceImp.getAllBooks();

        assertEquals(saved, books);
    }

    @Test
    public void testGetBookById() {

        List<BookEntity> books = Arrays.asList(new BookEntity(1, "Travis", "Book1", 109), new BookEntity(2, "Ronaldo", "Book2", 200));

        when(bookRepository.findById(1)).thenReturn(Optional.of(books.get(0)));
        when(bookRepository.findById(2)).thenReturn(Optional.of(books.get(1)));

        BookEntity bookById1 = bookServiceImp.getBooksById(1);
        BookEntity bookById2 = bookServiceImp.getBooksById(2);

        assertEquals(bookById1, books.get(0));
        assertEquals(bookById2, books.get(1));
    }

    @Test
    public void testGetBookByName() {

        List<BookEntity> books = Arrays.asList(new BookEntity(1, "Travis", "Book1", 109), new BookEntity(2, "Ronaldo", "Book2", 200));

        when(bookRepository.findByName("Book1")).thenReturn(Arrays.asList(books.get(0)));
        when(bookRepository.findByName("Book2")).thenReturn(Arrays.asList(books.get(1)));

        List<BookEntity> bookByName1 = bookServiceImp.getBooksByName("Book1");
        List<BookEntity> bookByName2 = bookServiceImp.getBooksByName("Book2");

        assertEquals(bookByName1, Arrays.asList(books.get(0)));
        assertEquals(bookByName2, Arrays.asList(books.get(1)));
    }

    @Test
    public void testGetBookByAuthor() {

        List<BookEntity> books = Arrays.asList(new BookEntity(1, "Travis", "Book1", 109), new BookEntity(2, "Ronaldo", "Book2", 200));

        when(bookRepository.findByAuthor("Travis")).thenReturn(Arrays.asList(books.get(0)));
        when(bookRepository.findByAuthor("Ronaldo")).thenReturn(Arrays.asList(books.get(1)));

        List<BookEntity> bookByAuthor1 = bookServiceImp.getBooksByAuthor("Travis");
        List<BookEntity> bookByAuthor2 = bookServiceImp.getBooksByAuthor("Ronaldo");

        assertEquals(bookByAuthor1, Arrays.asList(books.get(0)));
        assertEquals(bookByAuthor2, Arrays.asList(books.get(1)));
    }


    @Test
    public void testSavedBook() {

        BookEntity newBook = new BookEntity(4, "Harry", "Creative Thinking", 100);

        when(bookRepository.save(newBook)).thenReturn(newBook);

        bookServiceImp.saveBook(newBook);

        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    public void testUpdateBook() {

        Integer id = 6;
        BookEntity existingBook = new BookEntity(1, "Garry", "Book1", 300);
        Map<String, Object> updates = new HashMap<>();
        updates.put("author", "Larry");
        updates.put("name", "Sample");
        updates.put("price", 200);

        bookServiceImp.updateBook(id, existingBook, updates);

        verify(bookRepository, times(1)).save(existingBook);

    }

    @Test
    public void testDeleteBook() {

        Integer idToDelete = 1;

        bookServiceImp.deleteBook(idToDelete);

        verify(bookRepository, times(1)).deleteById(idToDelete);
    }


}
