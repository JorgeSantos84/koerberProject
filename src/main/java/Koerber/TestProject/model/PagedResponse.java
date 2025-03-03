package Koerber.TestProject.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class PagedResponse<T> {

    private final long totalElements;
    private final int totalPages;
    private final int pageNumber;
    private final int pageSize;
    private final List<T> content;

    public PagedResponse(Page<T> page) {
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.content = page.getContent();
    }
}
