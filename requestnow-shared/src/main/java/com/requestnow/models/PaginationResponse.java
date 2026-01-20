package com.requestnow.models;

import org.springframework.data.domain.Page;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationResponse<T> {

    private Integer currentPage;  // 1-based
    private int totalPages;
    private Integer pageSize;
    private int totalCount;

    private Integer previousPageNumber;
    private Integer nextPageNumber;

    @Builder.Default
    private List<T> items = List.of();

    public PaginationResponse(List<T> items, int totalCount, Integer pageNumber, int pageSize) {
        pageNumber = (pageNumber == null || pageNumber < 1) ? 1 : pageNumber;

        this.currentPage = pageNumber;
        this.totalCount = Math.max(totalCount, 0);

        List<T> safeItems = (items == null) ? List.of() : List.copyOf(items);
        this.items = safeItems;

        this.pageSize = safeItems.isEmpty() ? 0 : Math.max(pageSize, 0);

        if (pageSize <= 0) {
            this.totalPages = 0;
        } else {
            this.totalPages = (int) Math.ceil(this.totalCount / (double) pageSize);
        }

        // has prev/next & numbers
        if (getHasPreviousPage()) {
            this.previousPageNumber = this.currentPage - 1;
        }
        if (getHasNextPage()) {
            this.nextPageNumber = this.currentPage + 1;
        }
    }

    public static <T> PaginationResponse<T> create(Page<T> page) {
        int current = page.getNumber() + 1; // Spring es 0-based
        int totalPages = page.getTotalPages();
        boolean hasPrev = page.hasPrevious();
        boolean hasNext = page.hasNext();

        page.getContent();
        return PaginationResponse.<T>builder()
                .currentPage(current)
                .totalPages(totalPages)
                .pageSize(page.getSize())
                .totalCount((int) page.getTotalElements())
                .previousPageNumber(hasPrev ? current - 1 : null)
                .nextPageNumber(hasNext ? current + 1 : null)
                .items(List.copyOf(page.getContent()))
                .build();
    }

    public static <T> PaginationResponse<T> create(List<T> items, int totalCount, Integer pageNumber, int pageSize) {
        return new PaginationResponse<>(items, totalCount, pageNumber, pageSize);
    }

    public boolean getHasPreviousPage() {
        return currentPage != null && currentPage > 1;
    }

    public boolean getHasNextPage() {
        return currentPage != null && currentPage < totalPages;
    }
}
