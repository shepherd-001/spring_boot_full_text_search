package com.shepherd.spring_boot_full_text_search.data.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ArticleSearchRequest {
    @NotBlank(message = "Search text cannot be blank")
    private String searchText;
    @NotNull(message = "Enter page number")
    private int pageNumber;
}
