package com.shepherd.spring_boot_full_text_search.service;

import com.shepherd.spring_boot_full_text_search.data.dto.request.ArticleSearchRequest;
import com.shepherd.spring_boot_full_text_search.data.dto.response.ArticleSearchResponse;
import com.shepherd.spring_boot_full_text_search.data.model.Article;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ArticleService {
    List<Article> getAllArticles();
    Article getArticleById(UUID id);
    Article createArticle(Article article);
    void deleteArticle(UUID id);
    List<Article> searchArticles(String searchText);
    Page<ArticleSearchResponse> findArticles(ArticleSearchRequest request);
}
