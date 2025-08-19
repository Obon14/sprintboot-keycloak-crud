package com.example.keycloak.response;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PageResponse<T> {
    private int page;
    private int size;
    private int totalItem;
    private int totalPages;
    private List<T> items;

    public static <T> PageResponse<T> create(int page, int size, int totalItem, int totalPages, List<T> items) {
        return PageResponse.<T>builder()
                .items(items)
                .page(page)
                .size(size)
                .totalItem(totalItem)
                .totalPages(totalPages)
                .build();
    }
}
