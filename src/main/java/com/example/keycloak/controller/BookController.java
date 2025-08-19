package com.example.keycloak.controller;

import com.example.keycloak.model.Book;
import com.example.keycloak.response.ApiResponse;
import com.example.keycloak.response.PageResponse;
import com.example.keycloak.service.BookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Locale;

@Tag(name = "BOOK")
@RestController
@RequestMapping("/book")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final MessageSource messageSource;

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<Object>> addBook(@RequestBody Book book) {
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.success(bookService.addBook(book),
                            messageSource.getMessage("success.save.data", null, Locale.of("id", "ID"))));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.error(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(ApiResponse.error(messageSource.getMessage("error.internal.server"
            ,null, Locale.of("id", "ID")), null));
        }
    }

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<ApiResponse<PageResponse<Book>>> getAllBooks(
            @RequestParam(value = "book", required = false) String book,
            @RequestParam(value = "author", required = false) String author,
            Pageable pageable
    ) {
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.success(bookService.getAllBook(book, author, pageable),
                            messageSource.getMessage("success.load.data", null, Locale.of("en", "EN"))));
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(ApiResponse.error(e.getMessage(), null));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error(messageSource.getMessage("error.internal.server",
                    null, Locale.of("en", "EN")), null));
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> getBookById(@PathVariable("id") Integer id, @RequestBody Book book) {
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.success(bookService.updateBook(id, book),
                            messageSource.getMessage("success.edit.data", null,
                                    Locale.of("en", "EN"))));
        } catch (ResponseStatusException rse){
            return ResponseEntity.status(rse.getStatusCode()).body(ApiResponse.error(rse.getMessage(), null));
        } catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error(messageSource.getMessage("error.internal.server",
                            null, Locale.of("en", "EN")), null));
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<String>> deleteBookById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok()
                    .body(ApiResponse.success(bookService.deleteBook(id),
                            messageSource.getMessage("success.delete.data", null,
                                    Locale.of("id", "ID"))));
        } catch (ResponseStatusException rse){
            return ResponseEntity.status(rse.getStatusCode()).body(ApiResponse.error(rse.getMessage(), null));
        } catch (Exception e){
            return ResponseEntity.internalServerError()
                    .body(ApiResponse.error(messageSource.getMessage("error.internal.server",
                            null, Locale.of("en", "EN")), null));
        }
    }
}
