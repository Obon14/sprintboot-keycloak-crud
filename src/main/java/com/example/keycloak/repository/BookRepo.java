package com.example.keycloak.repository;

import com.example.keycloak.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepo extends JpaRepository<Book, Integer> {

    Page<Book> findAll(Specification<Book> spec, Pageable pageable);
}
