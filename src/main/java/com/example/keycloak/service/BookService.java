package com.example.keycloak.service;

import com.example.keycloak.model.Book;
import com.example.keycloak.response.PageResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    String addBook(Book book);
    PageResponse<Book> getAllBook(String search, String author, Pageable pageable);
    String updateBook(Integer id, Book book);
    String deleteBook(Integer id);
}
