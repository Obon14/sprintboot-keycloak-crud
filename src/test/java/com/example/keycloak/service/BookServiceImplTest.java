package com.example.keycloak.service;

import com.example.keycloak.model.Book;
import com.example.keycloak.repository.BookRepo;
import com.example.keycloak.response.PageResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class BookServiceImplTest {

    @Mock
    private BookRepo bookRepo;

    @InjectMocks
    private BookServiceImpl bookService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddBookSuccess(){
        Book book = new Book();

        book.setNameBook("Book_test");
        book.setAuthor("Author_test");

        String result = bookService.addBook(book);

        assertEquals("Success", result);
        verify(bookRepo, times(1)).save(book);
    }

    @Test
    void testAddBookFail(){
        Book book = new Book();
        book.setNameBook("Book_test");
        book.setAuthor("Author_test");

        doThrow(new RuntimeException("DB Error")).when(bookRepo).save(book);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookService.addBook(book);
        });

        assertEquals("DB Error", exception.getMessage());
        verify(bookRepo, times(1)).save(book);
    }

    @Test
    void testGetAllBooksSuccess(){

        Pageable pageable = PageRequest.of(0, 10);
        List<Book> books =List.of(new Book(1, "Book", "Alex"));
        Page<Book> bookPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepo.findAll(any(Specification.class), eq(pageable))).thenReturn(bookPage);

        PageResponse<Book> response = bookService.getAllBook(null, null, pageable);

        assertEquals(1, response.getItems().size());
        assertEquals("Book", response.getItems().get(0).getNameBook());
        assertEquals("Alex", response.getItems().get(0).getAuthor());
        assertEquals(0, response.getPage());
        assertEquals(10, response.getSize());

        verify(bookRepo, times(1)).findAll(any(Specification.class), eq(pageable));
    }

    @Test
    void testGetAllBook_WithBookFilter() {
        // Arrange
        Pageable pageable = PageRequest.of(0, 5);
        List<Book> books = List.of(new Book(2, "Spring Boot Guide", "jef"));
        Page<Book> bookPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepo.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(bookPage);

        // Act
        PageResponse<Book> response = bookService.getAllBook("Spring Boot Guide", null, pageable);

        // Assert
        assertEquals(1, response.getItems().size());
        assertEquals("Spring Boot Guide", response.getItems().get(0).getNameBook());
    }

    @Test
    void testGetAllBook_WithAuthorFilter() {
        // Arrange
        Pageable pageable = PageRequest.of(1, 5);
        List<Book> books = List.of(new Book(3, "Microservices", "jef"));
        Page<Book> bookPage = new PageImpl<>(books, pageable, books.size());

        when(bookRepo.findAll(any(Specification.class), eq(pageable)))
                .thenReturn(bookPage);

        // Act
        PageResponse<Book> response = bookService.getAllBook(null, "jef", pageable);

        // Assert
        assertEquals(1, response.getItems().size());
        assertEquals("Microservices", response.getItems().get(0).getNameBook());
    }
}
