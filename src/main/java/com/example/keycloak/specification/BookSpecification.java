package com.example.keycloak.specification;

import com.example.keycloak.model.Book;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecification {

    private BookSpecification(){
        throw new UnsupportedOperationException("This Is Utility class!");
    }

    public static Specification<Book> nameBook(String search){
        String searchBook = search.trim().toLowerCase();
        return (root, query, builder) -> builder.like(builder.lower(root.get("nameBook")), "%" + searchBook + "%");
    }

    public static Specification<Book> authorNameBook(String search){
        String searchAuthor = search.trim().toLowerCase();
        return (root, query, builder) -> builder.like(builder.lower(root.get("author")), "%" + searchAuthor + "%");
    }
}
