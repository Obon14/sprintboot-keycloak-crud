package com.example.keycloak.service;

import com.example.keycloak.model.Book;
import com.example.keycloak.repository.BookRepo;
import com.example.keycloak.response.PageResponse;
import com.example.keycloak.specification.BookSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addBook(Book book) {
        bookRepo.save(book);
        return "Success";
    }

    @Override
    public PageResponse<Book> getAllBook(String book, String author, Pageable pageable) {
        Specification<Book> spec = Specification.where(null);

        if(book != null && !book.isEmpty()) {
            spec = spec.and(BookSpecification.nameBook(book));
        }

        if(author != null && !author.isEmpty()) {
            spec = spec.and(BookSpecification.authorNameBook(author));
        }

        Page<Book> dataBook = bookRepo.findAll(spec, pageable);


        return PageResponse.create(pageable.getPageNumber(),
                pageable.getPageSize(),
                dataBook.getSize(),
                dataBook.getTotalPages(),
                dataBook.getContent());
    }

    @Override
    public String updateBook(Integer id, Book book) {
        Book data = getBookById(id);
        data.setNameBook(book.getNameBook());
        data.setAuthor(book.getAuthor());
        bookRepo.save(data);
        return "Success";
    }

    @Override
    public String deleteBook(Integer id) {
        Book data = getBookById(id);
        bookRepo.delete(data);
        return "Success";
    }

    private Book getBookById(Integer id) {
        return bookRepo.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }
}
