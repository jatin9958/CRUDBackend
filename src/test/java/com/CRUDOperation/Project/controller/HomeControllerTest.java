package com.CRUDOperation.Project.controller;

import java.util.Arrays;
import java.util.List;

import com.CRUDOperation.Project.entity.BookEntity;
import com.CRUDOperation.Project.model.BookDTO;
import com.CRUDOperation.Project.services.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@EnableWebMvc
public class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private BookService bookService;


    @Test
    public void testGetAllBooks() throws Exception {
        List<BookEntity> bookList = Arrays.asList(
                new BookEntity(4, "Tanjiro", "Day to Day findings", 403),
                new BookEntity(5, "Jatin", "Learn Stock Market", 1000),
                new BookEntity(270010, "Jatin", "My Anime lists", 1000),
                new BookEntity(1, "Steven Smith", "Java Fundamentals", 650),
                new BookEntity(2, "Larry 'O", "Spring Boot Basics", 1000),
                new BookEntity(3, "Alex", "C++ Fundamentals", 1000)
        );

        Mockito.when(bookService.getAllBooks()).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/library/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(bookList)));
    }

    @Test
    public void testGetBookById() throws Exception {

        BookEntity book = new BookEntity(5, "Jatin", "Learn Stock Market", 1000);

        Mockito.when(bookService.getBooksById(5)).thenReturn(book);

        mockMvc.perform(MockMvcRequestBuilders.get("/library/5"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(book)));
    }

    @Test
    public void testGetBookByName() throws Exception {
        List<BookEntity> books = Arrays.asList(
                new BookEntity(5, "Jatin", "Learn Stock Market", 1000)
        );
        Mockito.when(bookService.getBooksByName("Learn Stock Market")).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/library/byName/Learn Stock Market"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(books)));
    }

    @Test
    public void testGetBookByAuthor() throws Exception {
        List<BookEntity> bookList = Arrays.asList(
                new BookEntity(5, "Jatin", "Learn Stock Market", 1000),
                new BookEntity(270010, "Jatin", "My Anime lists", 1000)
        );

        Mockito.when(bookService.getBooksByAuthor("Jatin")).thenReturn(bookList);

        mockMvc.perform(MockMvcRequestBuilders.get("/library/byAuthor/Jatin"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(bookList)));
    }

    @Test
    public void testSaveBook() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor("Anny");
        bookDTO.setName("Learning");
        bookDTO.setPrice(500);

        String jsonRequest = objectMapper.writeValueAsString(bookDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/library/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateBook() throws Exception {

        Mockito.when(bookService.getBooksById(1)).thenReturn(new BookEntity(1, "Derren", "Kongg", 650));

        mockMvc.perform(MockMvcRequestBuilders.put("/library/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Kongg\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Book updated successfully."));
    }


}
