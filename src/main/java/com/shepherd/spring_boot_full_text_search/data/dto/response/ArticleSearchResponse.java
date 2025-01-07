package com.shepherd.spring_boot_full_text_search.data.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class ArticleSearchResponse {
    private String title;
    private String author;
    private String content;
    private String category;
    private String keywords;
    private LocalDate publicationDate;
}
